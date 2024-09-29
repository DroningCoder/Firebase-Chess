package com.example.chess

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class Invitations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_invitations)

        val clearBtn=findViewById<Button>(R.id.clearBtn)

        var invitesList:MutableList<String> = mutableListOf()
        val firestore = FirebaseFirestore.getInstance()

        var docid=intent.extras!!.getString("id").toString()
        var email=intent.extras!!.getString("email").toString()

        val adapter= ArrayAdapter<String?>(
            this@Invitations,
            android.R.layout.simple_list_item_1,
            invitesList as MutableList<String?>
        )
        val listView=findViewById<ListView>(R.id.invitationsListView)
        listView.adapter=adapter

        val docRef = firestore.collection("users").document(docid)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val doc = snapshot.data
                var invites = doc?.get("invites") as? MutableList<String>
                invitesList.clear()
                if (invites != null) {
                    invitesList.addAll(invites)
                }
                if (invitesList.size > 1 && invitesList.contains("You have 0 invites.")) {
                    invitesList.remove("You have 0 invites.")
                }
            }
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener{ parent, view, position, id->
            val selectedFriendEmail=invitesList[position]
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@Invitations)
            alertDialog.setMessage("Accept challenge from this friend?")
            alertDialog.setPositiveButton(
                "Yes"
            ) { _, _ ->
                firestore.collection("users")
                    .whereEqualTo("email",selectedFriendEmail)
                    .get()
                    .addOnSuccessListener { documents->
                        for(document in documents){
                            val gameId=document.get("gameId").toString()
                            val documentRef = docid.let { it1 -> firestore.collection("users").document(it1) }
                            documentRef.update("player","player2")
                            documentRef.update("gameId",gameId)
                            documentRef.update("invites",FieldValue.arrayRemove(selectedFriendEmail))

                            invitesList.remove(selectedFriendEmail)
                            if(invitesList.size==0){
                                invitesList.add("You have 0 invites.")
                                adapter.notifyDataSetChanged()
                                invitesList.remove("You have 0 invites.")
                            }
                            else{
                                adapter.notifyDataSetChanged()
                            }

                            var game_id=""

                            firestore.collection("games")
                                .whereEqualTo("gameId",gameId)
                                .get()
                                .addOnSuccessListener { documents ->
                                    for(document in documents){
                                        game_id=document.id.toString()
                                        val gameDocumentRef = game_id.let { it1 -> firestore.collection("games").document(it1) }
                                        gameDocumentRef.update("player2","online")

                                        val intent=Intent(this,Game::class.java)
                                        intent.putExtra("email",email)
                                        intent.putExtra("oppEmail",selectedFriendEmail)
                                        startActivity(intent)
                                    }
                                }
                        }
                    }
                    .addOnFailureListener{ exception->
                    }
            }
            alertDialog.setNegativeButton(
                "No"
            ) { _, _ ->
                val documentRef = docid.let { it1 -> firestore.collection("users").document(it1) }
                documentRef.update("invites",FieldValue.arrayRemove(selectedFriendEmail))
                invitesList.remove(selectedFriendEmail)
                if(invitesList.size==0){
                    invitesList.add("You have 0 invites.")
                    adapter.notifyDataSetChanged()
                    invitesList.remove("You have 0 invites.")
                }
                else{
                    adapter.notifyDataSetChanged()
                }
            }
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()
        }

        clearBtn.setOnClickListener {
            invitesList.clear()
            invitesList.add("You have 0 invites.")
            adapter.notifyDataSetChanged()

            val documentRef = docid.let { it1 -> firestore.collection("users").document(it1) }
            documentRef.update("invites",invitesList)
        }
    }

}
package com.example.chess

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import kotlin.coroutines.Continuation
import kotlin.random.Random
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class selectFriend : AppCompatActivity() {

    fun getRandomChar():Char{
        val chars = ('a'..'z') + ('A'..'Z')
        return chars.random()
    }

    fun createUniqueGameId():String{
        var uniqueGameId=""
        for(i in 0..9) {
            if (Random.nextBoolean()) {
                uniqueGameId += Random.nextInt(0, 10).toString()
            }
            else {
                uniqueGameId += getRandomChar().toString()
            }
        }
        return uniqueGameId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_friend)

        val firestore = FirebaseFirestore.getInstance()

        val docid=intent.extras!!.getString("id")
        val email=intent.extras!!.getString("email")
        val gameMode=intent.extras!!.getInt("gameMode")

        var friendsList:MutableList<String> = mutableListOf()

        val addFriendBtn=findViewById<Button>(R.id.addFriendBtn)

        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@selectFriend,
            android.R.layout.simple_list_item_1,
            friendsList as List<String?>
        )

        val listView=findViewById<ListView>(R.id.listView)
        listView.adapter=adapter

        listView.setOnItemClickListener{ parent, view, position, id->
            val selectedFriendEmail=friendsList[position]
            if(selectedFriendEmail!="You have no friends."){
                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@selectFriend)
                alertDialog.setMessage("Challenge this friend?")
                alertDialog.setPositiveButton(
                    "Yes"
                ) { _, _ ->

                    val draw_resign=hashMapOf(
                        "draw" to 0,
                        "resign" to false,
                        "stalemate" to false
                    )

                    firestore.collection("draw_resign")
                        .add(draw_resign)
                        .addOnSuccessListener { Document->
                            var option1=""
                            var option2=""

                            if(Random.nextBoolean()){
                                option1="player1"
                                option2="player2"
                            }
                            else{
                                option1="player2"
                                option2="player1"
                            }

                            val gameID=createUniqueGameId()

                            var fromPos:MutableList<Int> = mutableListOf()
                            var toPos:MutableList<Int> = mutableListOf()

                            var time:Int=0
                            if(gameMode==0)time=600000
                            else if(gameMode==1)time=900000
                            else if(gameMode==2)time=1800000
                            else if(gameMode==3)time=180000
                            else if(gameMode==4)time=180000
                            else if(gameMode==5)time=300000
                            else if(gameMode==6)time=60000
                            else if(gameMode==7)time=60000
                            else time=120000

                            var increment:Int=0
                            if(gameMode==1)increment=10000
                            else if(gameMode==4)increment=4000
                            else if(gameMode==7||gameMode==8)increment=1000

                            val game = hashMapOf(
                                "player1" to "online",
                                "player2" to "offline",
                                "white" to option1,
                                "black" to option2,
                                "turn" to option1,
                                "gameId" to gameID,
                                "fromPos" to fromPos,
                                "toPos" to toPos,
                                "promotedTo" to "",
                                "timer1" to time,
                                "timer2" to time,
                                "drawResignDocumentId" to Document.id,
                                "whiteCheckmate" to "no",
                                "blackCheckmate" to "no",
                                "increment" to increment
                            )

                            firestore.collection("games")
                                .add(game)
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Game Created",Toast.LENGTH_SHORT).show()

                                    val documentRef = docid?.let { it1 -> firestore.collection("users").document(it1) }
                                    documentRef?.update("player","player1")
                                    documentRef?.update("gameId",gameID)

                                    firestore.collection("users")
                                        .whereEqualTo("email",selectedFriendEmail)
                                        .get()
                                        .addOnSuccessListener { documents ->
                                            for(document in documents){
                                                val friendDocumentId=document.id
                                                val friendDocumentRef = friendDocumentId.let { it1 -> firestore.collection("users").document(it1) }
                                                friendDocumentRef.update("invites",FieldValue.arrayUnion(email))
                                            }
                                        }
                                        val intent=Intent(this, Game::class.java)
                                        intent.putExtra("email",email)
                                        intent.putExtra("oppEmail",selectedFriendEmail)
                                        startActivity(intent)


                                }
                                .addOnFailureListener{
                                    Toast.makeText(this,"Error. Could not create game.",Toast.LENGTH_SHORT).show()
                                }
                        }

                }
                alertDialog.setNegativeButton(
                    "No"
                ) { _, _ -> }
                val alert: AlertDialog = alertDialog.create()
                alert.setCanceledOnTouchOutside(true)
                alert.show()
            }
        }

        firestore.collection("users")
            .whereEqualTo("email",email)
            .get()
            .addOnSuccessListener { documents->
                for(document in documents){
                    var friends=document.get("friends") as? MutableList<String>
                    if (friends != null) {
                        if(friends.isEmpty()){
                            friends.add("You have no friends.")
                        }
                        friendsList.addAll(friends)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Error. Could not search for email", Toast.LENGTH_SHORT).show()
            }

        addFriendBtn.setOnClickListener {

            val newFriend=findViewById<TextInputEditText>(R.id.addFriend)
            val newFriendEmail=newFriend.text.toString()

            firestore.collection("users")
            .whereEqualTo("email",newFriendEmail)
            .get()
            .addOnSuccessListener { documents->
                if(documents.isEmpty){
                    Toast.makeText(this, "User does not exist.", Toast.LENGTH_SHORT).show()
                }
                else{
                    if(friendsList.contains(newFriendEmail)){
                        Toast.makeText(this,"Friend already added.",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if(friendsList.elementAt(0)=="You have no friends."){
                            friendsList.removeAt(0)
                        }
                        friendsList.add(newFriendEmail)
                        adapter.notifyDataSetChanged()
                        val documentRef = docid?.let { it1 -> firestore.collection("users").document(it1) }
                        if (documentRef != null) {
                            documentRef.update("friends", FieldValue.arrayUnion(newFriendEmail))
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Friend added.", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener{
                                    Toast.makeText(this,"Friend not added.", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "Error. Could not search for friend.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
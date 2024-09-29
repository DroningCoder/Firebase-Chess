package com.example.chess

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {

    private fun startActivityHomeScreen(id:String, email:String){
        val intent=Intent(this,HomeScreen::class.java)
        intent.putExtra("id",id)
        intent.putExtra("email",email)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val id=intent.extras!!.getString("id")
        val email=intent.extras!!.getString("email")

        val usernameText=findViewById<TextInputEditText>(R.id.username)
        val emailText=findViewById<TextInputEditText>(R.id.email)
        val passwordText=findViewById<TextInputEditText>(R.id.password)
        val friendsText=findViewById<TextInputEditText>(R.id.friends)

        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("users")
            .whereEqualTo("email",email)
            .get()
            .addOnSuccessListener { documents->
                for(document in documents){
                    val username=document.getString("username").toString()
                    val password=document.getString("password").toString()
                    val friends=document.get("friends") as MutableList<String>
                    usernameText.setText(username)
                    emailText.setText(email)
                    passwordText.setText(password)
                    val concatenatedFriends=friends.joinToString(separator="\n")
                    friendsText.setText(concatenatedFriends)
                }
            }
            .addOnFailureListener{ exception->
            }

        val homeBtn=findViewById<Button>(R.id.homeBtn)
        val editBtn=findViewById<Button>(R.id.editBtn)

        homeBtn.setOnClickListener {
            startActivityHomeScreen(id!!,email!!)
        }

        editBtn.setOnClickListener {
            val username=findViewById<TextView>(R.id.usernameText)
            val password=findViewById<TextView>(R.id.passwordText)
            val reEnterPassword=findViewById<TextView>(R.id.friendsText)
            val reEnterPasswordLayout=findViewById<TextInputLayout>(R.id.friendsLayout)

            editBtn.text="Save"
            username.setText("New Username")
            usernameText.isEnabled=true
            usernameText.isFocusableInTouchMode=true
            usernameText.isCursorVisible=true

            password.setText("New Password")
            passwordText.isEnabled=true
            passwordText.isFocusableInTouchMode=true
            passwordText.isCursorVisible=true

            reEnterPasswordLayout.endIconMode=TextInputLayout.END_ICON_PASSWORD_TOGGLE
            reEnterPassword.setText("Re Enter Password")
            friendsText.setText("")
            friendsText.isFocusableInTouchMode=true
            friendsText.isCursorVisible=true
            friendsText.inputType=InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            editBtn.setOnClickListener {
                val usname=usernameText.text.toString()
                val pass=passwordText.text.toString()
                val reEnterPass=friendsText.text.toString()
                if(pass!=reEnterPass){
                    Toast.makeText(this,"Passwords Do Not Match.", Toast.LENGTH_SHORT).show()
                }
                else{
                    val updates= hashMapOf(
                        "username" to usname,
                        "password" to pass
                    )
                    val userDocumentRef=id!!.let { it1 -> firestore.collection("users").document(it1) }
                    userDocumentRef.update(updates as Map<String, Any>).addOnSuccessListener {
                        Toast.makeText(this,"Changes Made Successfully.", Toast.LENGTH_SHORT).show()
                        startActivityHomeScreen(id,email!!)
                    }
                }
            }
        }
    }
}
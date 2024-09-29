package com.example.chess

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.auth

class sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        val email_input=findViewById<TextInputEditText>(R.id.email_id)
        val username_input=findViewById<TextInputEditText>(R.id.username)
        val password_input=findViewById<TextInputEditText>(R.id.enter_password)
        val confirm_password_input=findViewById<TextInputEditText>(R.id.confirm_password)
        val sign_up_btn=findViewById<Button>(R.id.sign_up_button)

        sign_up_btn.setOnClickListener{
            val email=email_input.text.toString()
            val username=username_input.text.toString()
            val password=password_input.text.toString()
            val confirmpassword=confirm_password_input.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "Please enter an Email ID", Toast.LENGTH_SHORT).show()
            }
            else if(username.isEmpty()){
                Toast.makeText(this, "Please select a Username", Toast.LENGTH_SHORT).show()
            }
            else if(password!=confirmpassword){
                Toast.makeText(this, "Passwords do not match",Toast.LENGTH_SHORT).show()
            }
            else{
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Creating an Account...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                Handler().postDelayed({
                    progressDialog.dismiss()
                    checkUserExists(email,username,password)
                    finish()
                }, 2000)

            }
        }
    }

    private fun checkUserExists(email: String, username: String, password: String) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("users")
            .whereEqualTo("email",email)
            .get()
            .addOnSuccessListener { documents->
                if(documents.isEmpty){
                    createUser(email,username,password)
                }
                else{
                    Toast.makeText(this, "Email already registered.",Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{ exception->
                Toast.makeText(this, "Error. Could not search for email",Toast.LENGTH_SHORT).show()
            }
    }

    private fun createUser(email: String, username: String, password: String) {
        val firestore = FirebaseFirestore.getInstance()

        var friends = mutableListOf<String>()
        var invites = mutableListOf<String>("You have 0 invites.")

        val user = hashMapOf(
            "email" to email,
            "password" to password,
            "username" to username,
            "friends" to friends,
            "gameId" to "",
            "player" to "",
            "invites" to invites
        )

        firestore.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()

                val intent=Intent(this, login::class.java)
                startActivity(intent)
            }
            .addOnFailureListener{e->
                Toast.makeText(this,"Error. Could not create user.",Toast.LENGTH_SHORT).show()
            }
    }
}
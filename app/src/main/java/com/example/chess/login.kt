package com.example.chess

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import java.util.logging.Handler

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val sign_up_text_btn=findViewById<TextView>(R.id.sign_up_txt)
        val spannableString=SpannableString("New User? Sign Up")
        val clickableSpan=object:ClickableSpan(){
            override fun onClick(widget: View) {
                val intent=Intent(this@login, sign_up::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText=true
            }
        }
        spannableString.setSpan(clickableSpan,10,17,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        sign_up_text_btn.text=spannableString
        sign_up_text_btn.movementMethod=LinkMovementMethod.getInstance()

        val email_input=findViewById<TextInputEditText>(R.id.email_id)
        val password_input=findViewById<TextInputEditText>(R.id.password)
        val login_btn=findViewById<Button>(R.id.login_button)

        login_btn.setOnClickListener{
            checkUserExists(email_input.text.toString(), password_input.text.toString())
        }
    }

    private fun checkUserExists(email: String, password:String) {
        if(email.isEmpty()){
            Toast.makeText(this, "Please enter an email ID.", Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show()
        }
        else{
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("users")
                .whereEqualTo("email",email)
                .get()
                .addOnSuccessListener { documents->
                    if(documents.isEmpty){
                        Toast.makeText(this, "Email not registered.", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        var correct_password=""
                        for(document in documents){
                            correct_password= document.getString("password").toString()
                        }
                        if(password!=correct_password){
                            Toast.makeText(this,"Incorrect Password.",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            for(document in documents){
                                val progressDialog = ProgressDialog(this)
                                progressDialog.setMessage("Logging in...")
                                progressDialog.setCancelable(false)
                                progressDialog.show()
                                android.os.Handler().postDelayed({
                                    progressDialog.dismiss()
                                    val intent=Intent(this,HomeScreen::class.java)
                                    intent.putExtra("id",document.id.toString())
                                    intent.putExtra("email",email)
                                    startActivity(intent)
                                    finish()
                                }, 2000)
                            }
                        }
                    }
                }
                .addOnFailureListener{ exception->
                    Toast.makeText(this, "Error. Could not search for email", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
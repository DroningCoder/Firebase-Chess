package com.example.chess

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.chess.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    fun startActivitySelectFriend(id:String,email:String,gameMode:Int){
        val intent= Intent(this,selectFriend::class.java)
        intent.putExtra("id",id)
        intent.putExtra("email",email)
        intent.putExtra("gameMode",gameMode)
        startActivity(intent)
    }

    fun level2play(play:Button,invites:Button,profile:Button,quit:Button,id:String,email:String){
        play.setText("10 min")
        invites.setText("15 | 10")
        profile.setText("30 min")
        quit.setText("Back")

        play.setOnClickListener {
            startActivitySelectFriend(id,email,0)
        }

        invites.setOnClickListener {
            startActivitySelectFriend(id,email,1)
        }

        profile.setOnClickListener {
            startActivitySelectFriend(id,email,2)
        }

        quit.setOnClickListener {
            level1(play,invites,profile,quit,id,email)
        }
    }

    fun level2invites(play:Button,invites:Button,profile:Button,quit:Button,id:String,email:String){
        play.setText("3 min")
        invites.setText("3 | 2")
        profile.setText("5 min")
        quit.setText("Back")

        play.setOnClickListener {
            startActivitySelectFriend(id,email,3)
        }

        invites.setOnClickListener {
            startActivitySelectFriend(id,email,4)
        }

        profile.setOnClickListener {
            startActivitySelectFriend(id,email,5)
        }

        quit.setOnClickListener {
            level1(play,invites,profile,quit,id,email)
        }
    }

    fun level2profile(play:Button,invites:Button,profile:Button,quit:Button,id:String,email:String){
        play.setText("1 min")
        invites.setText("1 | 1")
        profile.setText("2 | 1")
        quit.setText("Back")

        play.setOnClickListener {
            startActivitySelectFriend(id,email,6)
        }

        invites.setOnClickListener {
            startActivitySelectFriend(id,email,7)
        }

        profile.setOnClickListener {
            startActivitySelectFriend(id,email,8)
        }

        quit.setOnClickListener {
            level1(play,invites,profile,quit,id,email)
        }
    }

    fun level1(play:Button,invites:Button,profile:Button,quit:Button,id:String,email:String){
        play.setText("Rapid")
        invites.setText("Blitz")
        profile.setText("Bullet")
        quit.setText("Back")

        play.setOnClickListener {
            level2play(play,invites,profile,quit,id,email)
        }

        invites.setOnClickListener {
            level2invites(play,invites,profile,quit,id,email)
        }

        profile.setOnClickListener {
            level2profile(play,invites,profile,quit,id,email)
        }

        quit.setOnClickListener {
            level0(play,invites,profile,quit,id,email)
        }
    }

    fun level0(play:Button,invites:Button,profile:Button,quit:Button,id:String,email:String){
        play.setText("Play")
        invites.setText("Invites")
        profile.setText("Profile")
        quit.setText("Quit")

        play.setOnClickListener{
            level1(play,invites,profile,quit,id,email)
        }

        invites.setOnClickListener {
            val intent= Intent(this,Invitations::class.java)
            intent.putExtra("id",id)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        profile.setOnClickListener {
            val intent= Intent(this,Profile::class.java)
            intent.putExtra("id",id)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        quit.setOnClickListener {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@HomeScreen)
            alertDialog.setMessage("Do you really want to quit?")
            alertDialog.setPositiveButton(
                "Yes"
            ) { _, _ ->
                finishAffinity()
            }
            alertDialog.setNegativeButton(
                "No"
            ) { _, _ -> }
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)

        val id=intent.extras!!.getString("id")
        val email=intent.extras!!.getString("email")

        val play=findViewById<Button>(R.id.playBtn)
        val invites=findViewById<Button>(R.id.invitesBtn)
        val profile=findViewById<Button>(R.id.profileBtn)
        val quit=findViewById<Button>(R.id.quitBtn)

        val logo=findViewById<ImageView>(R.id.logo)
        logo.setImageResource(R.drawable.logo)

        if(id!=null&&email!=null){
            level0(play,invites,profile,quit,id,email)
        }
    }
}
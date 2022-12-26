package com.example.cleanse.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanse.R
import com.example.cleanse.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        v_email_user.text = "Email: " + Constants.LOGGED_IN_EMAIL
        v_first_name_user.text = "First Name: " + Constants.LOGGED_IN_1NAME
        v_last_name_user.text = "Last Name: " + Constants.LOGGED_IN_2NAME
        v_phone_user.text = "Phone: " + Constants.LOGGED_IN_MOBILE
        v_result_user.text = "Score: " + Constants.LOGGED_IN_SURVEY
        v_type_user.text = "Type: " + Constants.LOGGED_IN_TYPE


        btn_logoutu_user.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
        btn_oredernow_user.setOnClickListener{
            startActivity(Intent(this@MainActivity, BottomActivity::class.java))
        }
    }
}
package com.example.cleanse.ui.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanse.R
import com.example.cleanse.firestore.FirestoreClass
import com.google.firebase.auth.FirebaseAuth

class StartingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)
        var tv_app_name = findViewById<TextView>(R.id.tv_app_name)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController

            if(controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (FirebaseAuth.getInstance().currentUser!=null){ //return true = user has not log out
                FirestoreClass().getUserInfo() //get the current login data
                startActivity(Intent(this@StartingActivity, MainActivity::class.java))//redirect
            }
            else{
                startActivity(Intent(this, LoginActivity::class.java))//redirect to login page
            }
            finish()
        }, 3000)

        val typeface: Typeface =
            Typeface.createFromAsset(assets, "Sabandija-Bold.ttf")
        tv_app_name.typeface = typeface
    }

}
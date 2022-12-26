package com.example.cleanse.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cleanse.R
import com.example.cleanse.ui.adminpage.dashboard.OrderFragment
import com.example.cleanse.ui.adminpage.home.PostFragment
import com.example.cleanse.ui.adminpage.notifications.NoAccessFragment
import com.example.cleanse.ui.adminpage.notifications.ProfileFragment
import com.example.cleanse.utils.Constants
import kotlinx.android.synthetic.main.activity_bottom.*

class BottomActivity : AppCompatActivity() {

    private val OrderFragment = OrderFragment() //call fragment
    private val PostFragment = PostFragment() //call fragment
    private val ProfileFragment = ProfileFragment() //call fragment
    private val NoAccessFragment = NoAccessFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)
        replaceFragment(OrderFragment)

        //its like swithc case
        if (Constants.LOGGED_IN_TYPE == "Cleaner"){
            nav_view.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_dashboard -> replaceFragment(OrderFragment)
                    R.id.navigation_home -> replaceFragment(PostFragment)
                    R.id.navigation_notifications -> replaceFragment(ProfileFragment)
                }
                true
            }
        }
        else{
            nav_view.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_dashboard -> replaceFragment(OrderFragment)
                    R.id.navigation_home -> replaceFragment(NoAccessFragment)
                    R.id.navigation_notifications -> replaceFragment(ProfileFragment)
                }
                true
            }
        }
    }

    fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_bottom, fragment)
        transaction.commit()
    }
}
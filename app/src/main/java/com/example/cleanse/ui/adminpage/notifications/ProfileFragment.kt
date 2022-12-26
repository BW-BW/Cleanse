package com.example.cleanse.ui.adminpage.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cleanse.R
import com.example.cleanse.ui.activity.LoginActivity
import com.example.cleanse.utils.Constants
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var v: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        v = inflater.inflate(R.layout.fragment_profile, container, false)
        //get data from constant
        v.findViewById<TextView>(R.id.v_email).text = "Email: " + Constants.LOGGED_IN_EMAIL
        v.findViewById<TextView>(R.id.v_first_name).text = "First Name: " + Constants.LOGGED_IN_1NAME
        v.findViewById<TextView>(R.id.v_last_name).text = "Last Name: " + Constants.LOGGED_IN_2NAME
        v.findViewById<TextView>(R.id.v_phone).text = "Phone: " + Constants.LOGGED_IN_MOBILE
        v.findViewById<TextView>(R.id.v_result).text = "Score: " + Constants.LOGGED_IN_SURVEY
        v.findViewById<TextView>(R.id.v_type).text = "Type: " + Constants.LOGGED_IN_TYPE

        //will be used for button click
        v.findViewById<Button>(R.id.btn_logoutu).setOnClickListener(this)
        return v
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id){
                R.id.btn_logoutu -> {
                    FirebaseAuth.getInstance().signOut()
                    //Log.e("Click", "DOne")
                    startActivity(Intent(activity, LoginActivity::class.java))
                    activity?.finish()
                }
            }
        }
    }
}
package com.example.cleanse.ui.adminpage.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.cleanse.R
import com.example.cleanse.databinding.FragmentPostBinding
import com.example.cleanse.firestore.FirestoreClass
import com.example.cleanse.models.Service
import com.example.cleanse.ui.activity.BottomActivity
import com.example.cleanse.utils.Constants
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentPostBinding? = null
    private lateinit var v: View

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        v = inflater.inflate(R.layout.fragment_post, container, false)
        //will be used for button click
        v.findViewById<Button>(R.id.btn_post).setOnClickListener(this)

        return v
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id){
                R.id.btn_post -> {
                    Log.e("Test","CLICKED")
                    registerService()
                    startActivity(Intent(activity, BottomActivity::class.java))
                    activity?.finish()
                }
            }
        }
    }

    //register service
    private fun registerService() {
        if (validateRegisterDetails()) {

            val UserType = if (rb_deep.isChecked) {
                Constants.DEEPDEEP
            } else {
                Constants.BASICBASIC
            }
            val provider = Constants.LOGGED_IN_1NAME + " " + Constants.LOGGED_IN_2NAME
            val hour = et_hour.text.toString().trim { it <= ' ' }
            val minute = et_minutes.text.toString().trim { it <= ' ' }
            val service = Service( //access model user here
                Constants.LOGGED_IN_ID,
                provider,
                et_date.text.toString().trim { it <= ' ' },
                et_time.text.toString().trim { it <= ' ' },
                et_price.text.toString().trim { it <= ' ' },
                UserType,
                hour.toInt(),
                minute.toInt()
            )
            FirestoreClass().registerService(this@PostFragment, service)
            } else {

            }
        }

    //validate not empty
    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_date.text.toString().trim { it <= ' ' }) -> {
                false
            }
            TextUtils.isEmpty(et_time.text.toString().trim { it <= ' ' }) -> {
                false
            }
            TextUtils.isEmpty(et_price.text.toString().trim { it <= ' ' }) -> {
                false
            }
            TextUtils.isEmpty(et_hour.text.toString().trim { it <= ' ' }) -> {
                false
            }
            TextUtils.isEmpty(et_minutes.text.toString().trim { it <= ' ' }) -> {
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
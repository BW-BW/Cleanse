package com.example.cleanse.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cleanse.R
import com.example.cleanse.firestore.FirestoreClass
import com.example.cleanse.models.User
import com.example.cleanse.utils.Constants
import kotlinx.android.synthetic.main.activity_survey.*

class Survey : BaseActivity(), View.OnClickListener {

    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        if(intent.hasExtra(Constants.SURVEYUPDATE)) {
            mUserDetails = intent.getParcelableExtra(Constants.SURVEYUPDATE)!!
        }
        btn_submit.setOnClickListener(this@Survey)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {

                R.id.btn_submit -> {
                    showProgressDialog(resources.getString(R.string.please_wait))
                    updateUserProfileDetails()

                }
            }
        }
    }

    //survey done
    fun userProfileUpdateSuccess() { // for firestore class
        hideProgressDialog()

        Toast.makeText(
            this@Survey,
            resources.getString(R.string.survey_success),
            Toast.LENGTH_SHORT
        ).show()

        FirestoreClass().getUserInfo() //get the current login data
        startActivity(Intent(this@Survey, MainActivity::class.java))
        finish()
    }

    //survey calculation
    private fun updateUserProfileDetails() {
        val userHashMap = HashMap<String, Any>() //to udpate to firebase ??

        //score1
        val q1 = if (rad1_1.isChecked){
            1
        }
        else if (rad1_2.isChecked){
            2
        } else {
            3
        }

        //score2
        val q2 = if (rad2_1.isChecked){
            1
        }
        else if (rad2_2.isChecked){
            2
        } else {
            3
        }

        //score3
        val q3 = if (rad3_1.isChecked){
            0
        } else {
            3
        }

        //score4
        val q4 = if (rad4_1.isChecked){
            2
        } else {
            0
        }

        val SurveyScore : Int = q1 + q2 + q3 + q4

        userHashMap[Constants.COMPLETE_SURVEY] = SurveyScore
        Constants.LOGGED_IN_SURVEY = SurveyScore
        FirestoreClass().updateUserSurvey(
            this@Survey,
            userHashMap
        )
    }
}
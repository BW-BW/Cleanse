package com.example.cleanse.firestore

import android.app.Activity
import android.util.Log
import com.example.cleanse.models.Service
import com.example.cleanse.models.User
import com.example.cleanse.ui.activity.LoginActivity
import com.example.cleanse.ui.activity.RegisterActivity
import com.example.cleanse.ui.activity.Survey
import com.example.cleanse.ui.adminpage.dashboard.OrderFragment
import com.example.cleanse.ui.adminpage.home.PostFragment
import com.example.cleanse.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    //access firebase instance.
    private val database = FirebaseFirestore.getInstance()

    //register user
    fun registerUser(activity: RegisterActivity, userInfo: User) {
        database.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Can't Register, Try Again",
                    e
                )
            }
    }

    //register service
    fun registerService(activity: PostFragment, userInfo: Service) {

        database.collection(Constants.SERVICE)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Can't Post, Try Again",
                    e
                )
            }
    }

    //delete service
    fun deleteService() {
        database.collection(Constants.SERVICE)
            .document(Constants.LOGGED_IN_ID)
            .delete()
            .addOnSuccessListener {
                Log.e("Success","Success")
            }
            .addOnFailureListener {
                Log.e("Error","Error")
            }
    }

    //get user id
    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    //get user detail
    fun getUserDetails(activity: Activity) {
        database.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)!!
                when (activity) {
                    is LoginActivity -> {
                        activity.userLoggedInSuccess(user)
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is LoginActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error", e)
            }
    }

    //update user profile - survey score
    fun updateUserSurvey(activity: Activity, userHashMap: HashMap<String, Any>) {
        database.collection(Constants.USERS).document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                when (activity) {
                    is Survey -> {
                        activity.userProfileUpdateSuccess() // from survey.kt
                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is Survey -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error in updating survey score", e)
            }
    }

    //update service if its ordered
    fun updateService(userHashMap: HashMap<String, Any>, serviceID: String) {
        database.collection(Constants.SERVICE)
            .document(serviceID)
            .update(userHashMap)
            .addOnSuccessListener {
            }
            .addOnFailureListener {
            }
        }

    //get user info
    fun getUserInfo(){ //for starting activity
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            database.collection(Constants.USERS)
                .document(it)
                .get()
                .addOnSuccessListener { document ->
                    val user = document.toObject(User::class.java)
                    if (user!=null){
                        Constants.LOGGED_IN_1NAME = user.firstName //assign constant for profile
                        Constants.LOGGED_IN_2NAME = user.lastName
                        Constants.LOGGED_IN_EMAIL = user.email
                        Constants.LOGGED_IN_MOBILE = user.mobile
                        Constants.LOGGED_IN_TYPE = user.type
                        Constants.LOGGED_IN_SURVEY = user.surveyDone
                        Constants.LOGGED_IN_ID = user.id
                    } else { }
                }
                .addOnFailureListener { e ->
                    Log.e("Error", e.toString())
                }
        }
    }

    //for recycler view - cleaner
    fun getServices(fragment: OrderFragment){ //used in orderFragment - For Cleaner
        database.collection(Constants.SERVICE)
            .get()
            .addOnSuccessListener { document ->
                val serviceList: ArrayList<Service> = ArrayList() //create array of object
                for (i in document.documents){
                    val service = i.toObject(Service::class.java)
                    if (service != null && service.id == Constants.LOGGED_IN_ID ) { //so that only that user is shown
                        service.id = i.id
                        serviceList.add(service) //add the object
                    }
                }
                fragment.fetchedEvents(serviceList) //from orderfragmnet.kt
            }
    }

    //for recycle view - user
    fun getServicesUser(fragment: OrderFragment){ // For User
        database.collection(Constants.SERVICE)
            .get()
            .addOnSuccessListener { document ->
                val serviceList: ArrayList<Service> = ArrayList() //create array of object
                for (i in document.documents){
                    val service = i.toObject(Service::class.java)
                    if (service != null && service.status != "Ordered") { //so that only available service shown
                        service.id = i.id
                        serviceList.add(service) //add the object
                    }
                }
                fragment.fetchedEventsUser(serviceList) //from orderfragmnet.kt
            }
    }
}
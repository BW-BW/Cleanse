package com.example.cleanse.utils

/**
 * A custom object to declare all the constant values in a single file. The constant values declared here is can be used in whole application.
 */
object Constants {

    // Firebase Constants
    // This  is used for the collection name for USERS.
    const val USERS: String = "users"
    const val SERVICE: String = "services"

    const val CLEANSSSE: String = "Cleanse"

    //value get from current loged in, go to firestore line 90
    var LOGGED_IN_USERNAME: String = "logged_in_username"
    var LOGGED_IN_SURVEY: Int = 0
    var LOGGED_IN_ID: String = ""
    var LOGGED_IN_1NAME: String = "logged_in_firstName"
    var LOGGED_IN_2NAME: String = "logged_in_lastName"
    var LOGGED_IN_TYPE: String = "logged_in_type"
    var LOGGED_IN_MOBILE: Long = 0
    var LOGGED_IN_EMAIL: String = "logged_in_email"

    // Intent extra constants.
    const val SURVEYUPDATE: String = "extra_user_details"

    const val COMPLETE_SURVEY: String = "surveyDone"
    const val COMPLETE_STATUS: String = "status"
    const val COMPLETE_CUSTOMER: String = "customer"
    const val COMPLETE_CUSPHONE: String = "cusphone"

    //button choiches
    const val USERUSER: String = "User"
    const val CLEANERCLEANER: String = "Cleaner"

    const val DEEPDEEP: String = "Deep Cleaning"
    const val BASICBASIC: String = "Basic Cleaning"
}
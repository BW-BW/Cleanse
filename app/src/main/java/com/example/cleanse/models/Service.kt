package com.example.cleanse.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Service(
    var id: String = "",
    val provider: String = "",
    val date: String = "",
    val time: String = "",
    val price: String = "",
    val type: String = "",
    val durationHour: Int = 0,
    val durationMinutes: Int = 0,
    val status: String = "Pending",
    val customer: String = "",
    val cusphone: Long = 0): Parcelable
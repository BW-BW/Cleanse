package com.example.cleanse.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanse.R
import com.example.cleanse.firestore.FirestoreClass
import com.example.cleanse.models.Service
import com.example.cleanse.utils.Constants

open class ServiceUserAdapter(
    private val serviceListUser: ArrayList<Service>,
    private val context: Context

) : RecyclerView.Adapter<ServiceUserAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.textView11)
        var date: TextView = itemView.findViewById(R.id.textView21)
        var time: TextView = itemView.findViewById(R.id.textView31)
        var price: TextView = itemView.findViewById(R.id.textView41)
        var typezz: TextView = itemView.findViewById(R.id.textView51)
        var duration: TextView = itemView.findViewById(R.id.textView61)
        var serid: TextView = itemView.findViewById(R.id.textView71)
        var butbut1: Button = itemView.findViewById(R.id.button1)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_service_user, viewGroup, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = serviceListUser[position]
        holder.title.text = "Provider: " +service.provider
        holder.date.text = "Date: " +service.date
        holder.time.text = "Time: " + service.time
        holder.price.text = "Price: " + service.price + " RM"
        holder.typezz.text = "Type: " +service.type
        holder.duration.text = "" + service.durationHour + " Hour " + service.durationMinutes + " Minutes"
        holder.serid.text = service.id
        holder.butbut1.setOnClickListener{
            Log.e("DELETE", "DELETED")
            //get service id
            var serviceID = service.id
            Log.e("DELETED", service.id)
            val userHashMap = HashMap<String, Any>() //to udpate to firebase ??

            val statusupdated : String = "Ordered"
            val customername: String = Constants.LOGGED_IN_1NAME + " " + Constants.LOGGED_IN_2NAME
            val cusphone: Long = Constants.LOGGED_IN_MOBILE

            userHashMap[Constants.COMPLETE_STATUS] = statusupdated //status from pending to ordered
            userHashMap[Constants.COMPLETE_CUSTOMER] = customername //give customer name
            userHashMap[Constants.COMPLETE_CUSPHONE] = cusphone //give customer phone number
            // call firestore class, update service
            FirestoreClass().updateService(userHashMap, serviceID)
            }
        }

    override fun getItemCount(): Int {
        return serviceListUser.size
    }

}

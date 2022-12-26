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

open class ServiceAdapter(
    private val serviceList: ArrayList<Service>,
    private val context: Context
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.textView1)
        var date: TextView = itemView.findViewById(R.id.textView2)
        var time: TextView = itemView.findViewById(R.id.textView3)
        var price: TextView = itemView.findViewById(R.id.textView4)
        var typezz: TextView = itemView.findViewById(R.id.textView5)
        var duration: TextView = itemView.findViewById(R.id.textView6)
        var statss: TextView = itemView.findViewById(R.id.textView7)
        var cusnamee: TextView = itemView.findViewById(R.id.textView8)
        var cusphonee: TextView = itemView.findViewById(R.id.textView9)
        var butbut: Button = itemView.findViewById(R.id.button)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_service, viewGroup, false)
        return ViewHolder(v)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = serviceList[position]
        holder.title.text = "Provider: " +service.provider
        holder.date.text = "Date: " +service.date
        holder.time.text = "Time: " + service.time
        holder.price.text = "Price: " + service.price + " RM"
        holder.typezz.text = "Type: " +service.type
        holder.duration.text = "" + service.durationHour + " Hour " + service.durationMinutes + " Minutes"
        holder.statss.text = "Status: " + service.status
        holder.cusnamee.text = "Customer Name: " + service.customer
        holder.cusphonee.text = "Customer Phone Number: " + service.cusphone
        holder.butbut.setOnClickListener{
            Log.e("DELETE", "DELETED")
            FirestoreClass().deleteService()
            }
        }

    override fun getItemCount(): Int {
        return serviceList.size
    }
}

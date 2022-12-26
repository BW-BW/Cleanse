package com.example.cleanse.ui.adminpage.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cleanse.R

class NoAccessFragment : Fragment() {

    private lateinit var v: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        v = inflater.inflate(R.layout.fragment_no_access, container, false)
        v.findViewById<TextView>(R.id.textViewSorry).text = "Sorry, User Cannot Open This Page"
        return v
    }
}
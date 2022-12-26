package com.example.cleanse.ui.adminpage.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanse.R
import com.example.cleanse.adapters.ServiceAdapter
import com.example.cleanse.adapters.ServiceUserAdapter
import com.example.cleanse.databinding.FragmentOrderBinding
import com.example.cleanse.firestore.FirestoreClass
import com.example.cleanse.models.Service
import com.example.cleanse.utils.Constants
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {
    private lateinit var service: ArrayList<Service>
    private var _binding: FragmentOrderBinding? = null
    private lateinit var v: View
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.fragment_order, container, false)

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onResume() {
        super.onResume()
        if (Constants.LOGGED_IN_TYPE == "Cleaner"){
            FirestoreClass().getServices(this@OrderFragment)
        }
        else{
            FirestoreClass().getServicesUser(this@OrderFragment)
        }
    }

    //for firestore class
    fun fetchedEvents(serviceList: ArrayList<Service>){ //send object to recyler view
        service = serviceList
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ServiceAdapter(service, requireActivity()) //go to serviceadapter
        }
    }

    fun fetchedEventsUser(serviceList: ArrayList<Service>){ //send object to recyler view
        service = serviceList
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ServiceUserAdapter(service, requireActivity()) //go to serviceadapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
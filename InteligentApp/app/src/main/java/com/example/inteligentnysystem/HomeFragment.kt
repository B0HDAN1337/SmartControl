package com.example.inteligentnysystem

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private val dataReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val takeHumidity = intent?.getStringExtra("humidity")
            val takeTemperature = intent?.getStringExtra("temperature")
            val takeLight = intent?.getStringExtra("light")

            view?.findViewById<TextView>(R.id.dataHumidity)?.text = "$takeHumidity %"
            view?.findViewById<TextView>(R.id.dataTemperature)?.text = "$takeTemperature C"
            view?.findViewById<TextView>(R.id.dataLight)?.text = "$takeLight light"

            Log.d("homeFragment", "Give: $takeHumidity, $takeTemperature, $takeLight")

        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onStart() {
        super.onStart()
        val filter = IntentFilter("com.example.inteligentnysystem.DATA_RECEIVED")
        requireActivity().registerReceiver(dataReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(dataReceiver)
    }

}
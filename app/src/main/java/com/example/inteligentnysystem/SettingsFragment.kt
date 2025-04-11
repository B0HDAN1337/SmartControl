package com.example.inteligentnysystem

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class SettingsFragment : Fragment() {

    private lateinit var deviceListItem: ListView
    private lateinit var deviceListAdapter: ArrayAdapter<String>
    private val deviceNames = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val bluetoothManager: BluetoothManager = requireContext().getSystemService(BluetoothManager::class.java)
         val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
         val buttonConnect: Button = view.findViewById(R.id.buttonConntect)

        deviceListItem = view.findViewById(R.id.searched_devices)
        deviceListAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, deviceNames)
        deviceListItem.adapter = deviceListAdapter


        //Enable Bluetooth on click
        buttonConnect.setOnClickListener{
            if(!bluetoothAdapter.isEnabled)
            {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(enableBtIntent);
            }
            else // if bluetooth enabled, search devices
            {
                 bluetoothAdapter.startDiscovery()
            }
        }
    }

    //Search bluetooth devices
    private val receiver = object : BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent)
        {
            val action: String = intent.action!!
            if (action == BluetoothDevice.ACTION_FOUND) {
                val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!
                val deviceName = device.name
                val deviceAddress = device.address

                //add device name and mac address to ListView
                val displayDevices = "$deviceName\n$deviceAddress"

                if(!deviceNames.contains(displayDevices))
                {
                    deviceNames.add(displayDevices)
                    deviceListAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        requireActivity().registerReceiver(receiver, filter)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(receiver)
    }
}


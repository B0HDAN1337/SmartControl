package com.example.inteligentnysystem

import android.annotation.SuppressLint
import android.app.Service
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID


class BluetoothService : Service() {


    private val binder = LocalBinder()
    private var socket: BluetoothSocket? = null
    private var connectedThread: ConnectedThread? = null

    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    inner class LocalBinder : Binder() {
        fun getService(): BluetoothService = this@BluetoothService
    }

    override fun onBind(intent: Intent?): IBinder = binder

    @SuppressLint("MissingPermission")
    fun connectToDevice(device: BluetoothDevice)
    {
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid)
            socket?.connect()
            Log.d("BluetoothService", "Connected to ${device.name}")
            connectedThread = ConnectedThread(socket!!)
            connectedThread?.start()
        } catch (e: IOException)
        {
            e.printStackTrace()
            try {
                socket?.close()
            } catch (closeEx: IOException)
            {
                closeEx.printStackTrace()
            }
        }
    }

    fun disconnect()
    {
        connectedThread?.cancel()
        socket?.close()
        connectedThread = null
        socket = null
        Log.d("BluetoothService", "Disconnected")
    }

    inner class ConnectedThread(private val bluetoothSocket: BluetoothSocket) : Thread()
    {
        private val inputStream: InputStream = bluetoothSocket.inputStream
        private val outputStream: OutputStream = bluetoothSocket.outputStream

        override fun run()
        {
            val buffer = ByteArray(1024)
            while (true)
            {
                try {
                    val bytes = inputStream.read(buffer)
                    val received = String(buffer, 0, bytes)
                    var receivedSplit = received.split(" ")
                    if(receivedSplit.size == 3)
                    {
                        val humidity = receivedSplit[0]
                        val temperature = receivedSplit[1]
                        val light = receivedSplit[2]
                        Log.d("BluetoothService", "Received: $humidity, $temperature, $light")

                        //Send Broadcast
                        val intent = Intent("com.example.inteligentnysystem.DATA_RECEIVED")
                        intent.putExtra("humidity", humidity)
                        intent.putExtra("temperature", temperature)
                        intent.putExtra("light", light)
                        sendBroadcast(intent)
                    }
                }catch (e: IOException)
                {
                    Log.d("BluetoothService", "Disconnected", e)
                    break
                }
            }
        }

        fun cancel()
        {
            try {
                bluetoothSocket.close()
            } catch (e: IOException)
            {
                Log.e("BluetoothService", "Error closing socket", e)
            }
        }
    }

}
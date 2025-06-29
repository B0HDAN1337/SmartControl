package com.example.inteligentnysystem.Network

import android.util.Log
import com.example.inteligentnysystem.Data.SensorData
import okhttp3.Call
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

object SensorService {
    fun send(sensorData: SensorData) {
        val json = JSONObject()
        json.put("temperature", sensorData.Temperature)
        json.put("humidity", sensorData.Humidity)
        json.put("light", sensorData.Light)
        json.put("timestamp", sensorData.Timestamp)

        val requestBody = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(), json.toString()
        )

        val request = Request.Builder()
            .url("http://192.168.43.80:2000/api/SensorData").post(requestBody).build()

        ApiService.client.newCall(request).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("SensorDataSender", "Failed to send data: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("SensorDataSender", "Server responsed: ${response.code}")
            }
        })
    }

}
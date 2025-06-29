package com.example.inteligentnysystem.Data

import android.icu.text.DateFormat
import java.util.Date

data class SensorData(
    val Temperature: Float,
    val Humidity: Float,
    val Light: Float,
    val Timestamp: String
)
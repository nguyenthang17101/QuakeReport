package com.example.quakereportx

import java.text.SimpleDateFormat
import java.util.*

data class QuakeEvents(val mag: Double,  val  place: String, val time : Long) {
    val dateString: String
    val timeString: String


    init {
        val dateFormatter = SimpleDateFormat("dd MMM, yyyy", Locale("VIE"))
        val date = Date(time)
        dateString = dateFormatter.format(date)
        dateFormatter.applyPattern("hh:mm, a")
        timeString = dateFormatter.format(date)
    }
}
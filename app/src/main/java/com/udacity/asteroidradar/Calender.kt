package com.udacity.asteroidradar

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class CustomCalender{
//    var calendar : Calendar = Calendar.getInstance()
    @SuppressLint("SimpleDateFormat")
    private var sdf : SimpleDateFormat? = SimpleDateFormat("yyyy-MM-dd")

    fun getDay(): String {
        val calendar : Calendar = Calendar.getInstance()
        return sdf!!.format(calendar.time)
    }

    /*private fun getDates() {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        val today = dateFormat.format(currentTime)
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val lastTime = calendar.time
        lastDay = dateFormat.format(lastTime)
    }*/

    fun getSeventhDay(): String {
        val calendar : Calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        return sdf!!.format(calendar.time)
    }

}


//fun getDay(date: Date): Int {
//        calendar = Calendar.getInstance()
//        calendar.time = date
//        calendar.time
//        return calendar[Calendar.da]
//    }
package com.example.listedassignment.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
        fun getDate(date: String): String {
            val string = date
            val date: Date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(
                string.replace(
                    "Z$".toRegex(),
                    "+0000"
                )
            ) as Date

            return SimpleDateFormat("dd-MM-yyyy").format(date).toString()
        }
    }
}
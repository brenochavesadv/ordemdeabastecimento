package br.com.brtecno.ordemdeabastecimento.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat

class ConvertDate {

    fun StringToTimestamp(dateString: String, format: String = "yyyy-MM-dd HH:mm:ss"): Timestamp? {
        return try {
            val formatter = SimpleDateFormat(format)
            val date = formatter.parse(dateString)
            Timestamp(date.time)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
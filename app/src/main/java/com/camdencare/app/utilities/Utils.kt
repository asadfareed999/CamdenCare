package com.camdencare.app.utilities

import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun parseDate(strBackendDate: String): String {
            Logging.debug("client end date : $strBackendDate")
            val sdfBackend = SimpleDateFormat(SDF_BACKEND, Locale.ENGLISH)
            val dateBackend = sdfBackend.parse(strBackendDate)
            val sdfClientEnd = SimpleDateFormat(SDF_CLIENTEND, Locale.ENGLISH)
            val strDateClientEnd = sdfClientEnd.format(dateBackend)
            Logging.debug("client end date : $strDateClientEnd")
            return strDateClientEnd
        }
    }
}
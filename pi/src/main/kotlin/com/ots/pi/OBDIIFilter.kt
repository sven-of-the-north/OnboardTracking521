package com.ots.pi

import java.io.File
import java.util.logging.Logger

class OBDIIFilter {
    private val allowedCodes: List<String> = listOf("B1430", "P1270", "P1230", "P1231", "P1232", "C1095", "C1096")
    private val LOGGER = Logger.getLogger("OBDIIFilter")

    fun filterFile(latestFile: File): List<String> {
        val ret: MutableList<String> = mutableListOf()
        while (latestFile.readLines().size == 0) {
            Thread.sleep(1000)
        }
        latestFile.readLines().forEach {
            val splitCode: List<String> = it.split(",")
            try {
                val code: String = splitCode[0].replace(" ", "")
                if (code in allowedCodes) {
                    ret.add(it)
                }
            } catch(e: NumberFormatException) {
                LOGGER.warning("NumberFormatException in reader")
            }
        }
        return ret
    }
    /* for testing not implementing filtering
    fun updateAllowedCodes(updatedAllowedCodes: List<String>) {
        allowedCodes = updatedAllowedCodes
    }
    */
}
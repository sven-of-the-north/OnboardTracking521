package com.ots.pi

import java.io.File
import java.util.logging.Logger

class OBDIIFilter {
    // private var allowedCodes: List<String> = listOf()
    private val LOGGER = Logger.getLogger("OBDIIFilter")

    fun filterFile(latestFile: File): List<String> {
        val ret: MutableList<String> = mutableListOf()
        latestFile.readLines().forEach {
            // for testing not implementing filtering
            // if (it in allowedCodes) ret.add(it)
            ret.add(it)
        }
        return ret
    }
    /* for testing not implementing filtering
    fun updateAllowedCodes(updatedAllowedCodes: List<String>) {
        allowedCodes = updatedAllowedCodes
    }
    */
}
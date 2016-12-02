package com.ots.pi

import com.ots.pi.data.TemporaryStorageController
import org.springframework.stereotype.Component
import java.io.File
import java.util.logging.Logger

@Component
class OBDIIReader(val filter: OBDIIFilter, val storageController: TemporaryStorageController, val properties: OBDIIProperties) : Runnable {

    val rootDirectory: File = File(properties.rootDirectory)

    private var latestDate: Date = Date(0, 0, 0, 0, 0, 0)
    private val LOGGER = Logger.getLogger("OBDIIReader")

    class Date(var year: Int, var month: Int, var day: Int, var hour: Int, var min: Int, var sec: Int) : Comparable<Date> {
        override fun compareTo(other: Date): Int {
            if (other.year != year) {
                return year - other.year
            } else if (other.month != month) {
                return month - other.month
            } else if (other.day != day) {
                return day - other.day
            } else if (other.hour != hour) {
                return hour - other.hour
            } else if (other.min != min) {
                return min - other.min
            } else {
                return sec - other.sec
            }
        }

        override fun toString(): String {
            return "$year-$month-$day-$hour-$min-$sec"
        }
    }

    override fun run() {
        val latestFile = findFileToRead()
        if (latestFile !== null) {
            val filteredCodes: List<String>? = filter.filterFile(latestFile)
            filteredCodes?.forEach {
                val splitCode: List<String> = it.split(",")
                try {
                    val code: String = splitCode[0].replace(" ", "")
                    storageController.store(code, splitCode[1])
                } catch(e: NumberFormatException) {
                    LOGGER.warning("NumberFormatException in reader")
                }
            }
        }
    }

    /*
     * Returns the file to read or null if no file needs to be read
     */
    private fun findFileToRead(): File? {
        var retFile: File? = null
        rootDirectory.listFiles().forEach {
            val date: Date? = getDateFromFileName(it.name)
            if (date !== null && date > latestDate) {
                latestDate = date
                retFile = it
            }
        }
        return retFile
    }

    /*
     * File names are formatted as OBDII2016-12-01-18-17-37
     * Returns a Date object or null if the file name cannot be translated
     */
    private fun getDateFromFileName(name: String): Date? {
        val regex: Regex = Regex("OBDII(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)-(\\d\\d)-(\\d\\d)-(\\d\\d)\\.csv")
        val matchResult: MatchResult? = regex.matchEntire(name)
        // First match will be the whole thing so 6 + 1 = 7
        if (matchResult !== null && matchResult.groups.size == 7) {
            val convertStringToInt: (List<String>) -> List<Int> = { stringList ->
                val ret: MutableList<Int> = mutableListOf()
                var index = 0
                var isFirst = true
                stringList.forEach {
                    if (isFirst) {
                        isFirst = false
                    } else {
                        try {
                            ret.add(index = index, element = it.toInt())
                            index++
                        } catch (e: NumberFormatException) {
                            LOGGER.warning("Cannot convert to number $it")
                        }
                    }
                }
                ret
            }

            val matchGroups: List<Int> = convertStringToInt(matchResult.groupValues)
            return Date(matchGroups[0], matchGroups[1], matchGroups[2], matchGroups[3], matchGroups[4], matchGroups[5])
        }
        return null
    }
}


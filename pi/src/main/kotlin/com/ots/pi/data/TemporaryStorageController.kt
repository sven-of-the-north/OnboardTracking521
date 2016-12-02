package com.ots.pi.data

import java.util.logging.Logger


class TemporaryStorageController {
    private val LOGGER = Logger.getLogger("storage")
    private val codesToBeSent: MutableList<OBDIICode> = mutableListOf()
    private val lock: Any = Any()

    fun store(code: String, name: String) {
        synchronized(lock) {
            codesToBeSent.add(OBDIICode(code, name))
        }
    }

    fun retrieve(): MutableList<OBDIICode> {
        synchronized(lock) {
            val temp: MutableList<OBDIICode> = mutableListOf()
            temp.addAll(codesToBeSent)
            codesToBeSent.clear()
            return temp
        }
    }
}



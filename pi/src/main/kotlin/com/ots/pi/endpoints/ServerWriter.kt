package com.ots.pi.data.endpoints

import com.ots.pi.data.OBDIICode
import com.ots.pi.data.TemporaryStorageController
import org.springframework.stereotype.Component
import java.net.URL
import java.util.logging.Logger

@Component
open class ServerWriter(val storageController: TemporaryStorageController) {
    private val LOGGER = Logger.getLogger("ServerWriter")
    fun send(url: URL) {
        val obdiiList: List<OBDIICode>? = storageController.retrieve()
        obdiiList?.forEach {
            sendDataToServer(it, url)
        }
    }

    private fun sendDataToServer(obdiiCode: OBDIICode, url: URL) {
        //val connection: URLConnection = url.openConnection()
        LOGGER.info("${obdiiCode.code}")
    }
}
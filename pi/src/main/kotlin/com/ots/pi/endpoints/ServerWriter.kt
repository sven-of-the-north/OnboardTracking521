package com.ots.pi.endpoints

import com.ots.pi.data.OBDIICode
import com.ots.pi.data.TemporaryStorageController
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URL
import java.util.logging.Logger

@Component
open class ServerWriter(val storageController: TemporaryStorageController, val restTemplate: RestTemplate) {
    private val LOGGER = Logger.getLogger("ServerWriter")
    fun send(url: URL) {
        val obdiiList: List<OBDIICode>? = storageController.retrieve()
        LOGGER.info("Retrieved $obdiiList")
        obdiiList?.forEach {
            sendDataToServer(it, url)
        }
    }

    private fun sendDataToServer(obdiiCode: OBDIICode, url: URL) {
        val jsonObject: JsonOBDIICode = JsonOBDIICode(1, obdiiCode.timestamp, obdiiCode.code, obdiiCode.name)
        restTemplate.postForLocation(url.toString(), jsonObject)
    }

    private data class JsonOBDIICode(val carId: Int, val time: Long, val eventName: String, val value: String)
}
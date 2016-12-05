package com.ots.pi.endpoints

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import java.net.URL

@Controller
@RequestMapping(value = "/send")
open class ServerSendEndpoint(val serverWriter: ServerWriter) {
    @RequestMapping(method = arrayOf(RequestMethod.GET))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun doGet(@RequestParam("address") address: String, @RequestParam("carId") carId: Int) {
        serverWriter.send(URL(address), carId)
    }
}
package com.ots.pi

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "obdii")
class OBDIIProperties {

    var rootDirectory: String

    constructor() {
        rootDirectory = ""
    }
}
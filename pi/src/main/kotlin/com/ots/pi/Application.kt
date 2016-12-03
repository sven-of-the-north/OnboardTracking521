package com.ots.pi

import com.ots.pi.data.TemporaryStorageController
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.web.client.RestTemplate

@SpringBootApplication
open class Application {

    @Bean
    open fun storageController(): TemporaryStorageController {
        return TemporaryStorageController()
    }

    @Bean
    open fun filter(): OBDIIFilter {
        return OBDIIFilter()
    }

    @Bean
    open fun taskScheduler(): TaskScheduler {
        return ThreadPoolTaskScheduler()
    }

    @Bean
    open fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    open fun schedulingRunner(scheduler: TaskScheduler, obdiiReader: OBDIIReader): CommandLineRunner {
        return object : CommandLineRunner {
            override fun run(vararg args: String?) {
                scheduler.scheduleAtFixedRate(obdiiReader, 5000)
            }
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
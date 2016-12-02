package com.ots.pi

import com.ots.pi.data.TemporaryStorageController
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import java.io.File
import kotlin.system.exitProcess

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
    open fun schedulingRunner(scheduler: TaskScheduler, obdiiReader: OBDIIReader): CommandLineRunner {
        return object : CommandLineRunner {
            override fun run(vararg args: String?) {
                if (args.size != 1) {
                    println("Must pass directory location of OBDII csv files")
                    exitProcess(1)
                }
                val rootDirectoryString: String? = args[0]
                val rootDirectory: File = File(rootDirectoryString)
                if (rootDirectory.isDirectory) {
                    scheduler.scheduleAtFixedRate(obdiiReader, 5000)
                } else {
                    println("Second argument must be a valid directory")
                    exitProcess(1)
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
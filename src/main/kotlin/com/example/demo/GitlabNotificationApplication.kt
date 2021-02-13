package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    ApiContextInitializer.init();
    runApplication<DemoApplication>(*args)
}

package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hook")
class HookController {

    @GetMapping
    fun getPaySystemsByCurrencyAndCurrentUser(): List<String> {
        return listOf("test")
    }
}

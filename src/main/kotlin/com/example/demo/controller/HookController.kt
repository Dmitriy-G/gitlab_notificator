package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hook")
class HookController {
    @GetMapping
    fun getSomeData(): List<String> {
        return listOf("test")
    }

    @PostMapping
    fun catchGitlabHook(@RequestBody body: String): List<String> {
        println(body)
        return listOf("test")
    }
}

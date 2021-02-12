package com.example.demo.controller

import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hook")
class HookController(
        private val userService: UserService
) {

    @GetMapping
    fun getSomeData(): List<String> {
        return listOf("test")
    }

    @PostMapping
    fun catchGitlabHook(@RequestBody body: String): List<String> {
        println(body)
        //val user = userService.getUserByUsername("dgorokhovtsev")
        userService.registerByUsername("testChat", "dgorokhovtsev")
        //println(user)
        return listOf("test")
    }
}

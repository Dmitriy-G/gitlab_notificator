package com.example.demo.controller

import com.example.demo.service.GitlabRequestService
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.core.Context
import org.gitlab4j.api.HookManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hook")
class HookController(
        private val gitlabRequestService: GitlabRequestService,
        private val webHookManager: HookManager
) {

    @GetMapping
    fun getSomeData(): List<String> {
        return listOf("test")
    }

    @PostMapping
    fun catchGitlabHook(@Context request: HttpServletRequest): String {
        webHookManager.handleEvent(request)
        //gitlabRequestService.resolveGitlabRequest(body)
        return ""
    }
}

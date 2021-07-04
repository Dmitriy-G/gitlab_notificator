package com.example.demo.controller

import com.example.demo.model.Notification
import com.example.demo.service.GitlabRequestService
import com.example.demo.service.NotificationSenderService
import com.example.demo.service.UserService
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
        private val webHookManager: HookManager,
        private val telegramNotificationSenderService: NotificationSenderService,
        private val userService: UserService
) {

    @GetMapping
    @Deprecated("For test")
    fun getSomeData(): List<String> {
        val user = userService.getUserByUsername("test")
        if (user != null) telegramNotificationSenderService.send(Notification(user.channelId, "Message"))
        return listOf("test")
    }

    @PostMapping
    fun catchGitlabHook(@Context request: HttpServletRequest): String {
        webHookManager.handleEvent(request)
        return ""
    }
}

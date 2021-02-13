package com.example.demo.gitlab

import javax.annotation.PostConstruct
import org.gitlab4j.api.webhook.WebHookManager
import org.springframework.stereotype.Component

@Component
class WebHookManagerImpl(
        private val webHookResolveListener: WebHookResolveListener
) : WebHookManager() {

    @PostConstruct
    fun init() {
        super.addListener(webHookResolveListener)
    }
}

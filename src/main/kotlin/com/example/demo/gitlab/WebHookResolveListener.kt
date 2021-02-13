package com.example.demo.gitlab

import com.example.demo.service.GitlabRequestService
import org.gitlab4j.api.webhook.MergeRequestEvent
import org.gitlab4j.api.webhook.WebHookListener
import org.springframework.stereotype.Component

@Component
class WebHookResolveListener(
        private val gitlabRequestService: GitlabRequestService
) : WebHookListener {
    override fun onMergeRequestEvent(event: MergeRequestEvent) {
        gitlabRequestService.resolveGitlabRequest(event)
    }
}

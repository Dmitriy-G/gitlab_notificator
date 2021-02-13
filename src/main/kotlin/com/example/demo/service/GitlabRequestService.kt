package com.example.demo.service

import org.gitlab4j.api.webhook.MergeRequestEvent

interface GitlabRequestService {
    fun resolveGitlabRequest(event: MergeRequestEvent)
}

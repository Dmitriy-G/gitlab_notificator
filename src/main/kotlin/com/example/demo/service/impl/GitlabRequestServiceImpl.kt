package com.example.demo.service.impl

import com.example.demo.model.Notification
import com.example.demo.service.GitlabRequestService
import com.example.demo.service.NotificationSenderService
import com.example.demo.service.UserService
import mu.KotlinLogging
import org.gitlab4j.api.webhook.MergeRequestEvent
import org.springframework.stereotype.Service

@Service
class GitlabRequestServiceImpl(
        private val notificationSenderService: NotificationSenderService,
        private val userService: UserService
) : GitlabRequestService {
    private val logger = KotlinLogging.logger {}

    override fun resolveGitlabRequest(event: MergeRequestEvent) {
        val assigneeIds = event.assignees ?: return
        val mergeRequestUrl = event.objectAttributes.url.replace("_", "\\_")

        val message = String.format("You have been assigned a merge request %s", mergeRequestUrl)
        assigneeIds.map {
            val user = userService.getUserById(it.id.toString())
            if (user == null) {
                logger.warn(String.format("User with id = %s not registered in Gitlab Notificator", it.id))
                return
            }
            val notification = Notification(user.channelId, message)
            notificationSenderService.send(notification)
        }
    }
}

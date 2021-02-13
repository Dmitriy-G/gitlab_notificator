package com.example.demo.service.impl

import com.example.demo.model.Notification
import com.example.demo.sender.TelegramBotSender
import com.example.demo.service.NotificationSenderService
import com.example.demo.service.UserService
import org.springframework.stereotype.Service

@Service
class TelegramNotificationSenderServiceImpl (
        val telegramBotSender: TelegramBotSender
) : NotificationSenderService {
    override fun send(notification: Notification): Boolean {
        val chatId: String = notification.channelId
        val messageText: String = notification.message
        telegramBotSender.sendMsg(chatId, messageText)
        return true
    }
}

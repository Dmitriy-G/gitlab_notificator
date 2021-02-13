package com.example.demo.service

import com.example.demo.model.Notification

interface NotificationSenderService {
    fun send(notification: Notification) : Boolean
}

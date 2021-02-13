package com.example.demo.sender

import com.example.demo.service.UserService
import javax.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException

import org.telegram.telegrambots.meta.TelegramBotsApi







@Component
class TelegramBotSender(
        val userService: UserService
) : TelegramLongPollingBot() {

    private val logger = KotlinLogging.logger {}

    //TODO: move to properties
    private val telegramBotName = ""
    private val telegramBotToken = ""


    @PostConstruct
    private fun postConstruct() {
        val telegramBotsApi = TelegramBotsApi()
        try {
            telegramBotsApi.registerBot(this)
        } catch (e: TelegramApiRequestException) {
            e.printStackTrace()
        }
    }

    override fun getBotToken(): String {
        return this.telegramBotToken
    }

    override fun getBotUsername(): String {
        return this.telegramBotName
    }

    override fun onUpdateReceived(update: Update) {
        val chatId: String = update.message.chatId.toString()
        val messageText = update.message.text
        //start username
        if (messageText.contains("start")) {
            val username = messageText.split(" ")[1]
            val errorMessage = String.format("Error while registration user with username %s", username)
            try {
                val gitlabUser = userService.registerByUsername(chatId, username)
                if (gitlabUser != null) {
                    val message = String.format("%s was added to subscribers", gitlabUser.name)
                    sendMsg(chatId, message)
                } else {
                    sendMsg(chatId, errorMessage)
                }
            } catch (e: Exception) {
                logger.error { e }
                sendMsg(chatId, errorMessage)
            }
        } else {
            val message = "Unknown command: $messageText"
            sendMsg(chatId, message)
        }
    }

    fun sendMsg(chatId: String, message: String) {
        val sendMessage = SendMessage()
        sendMessage.enableMarkdown(true)
        sendMessage.chatId = chatId
        sendMessage.text = message
        execute(sendMessage)
    }
}

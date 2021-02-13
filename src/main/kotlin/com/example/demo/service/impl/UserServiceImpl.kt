package com.example.demo.service.impl

import com.example.demo.model.GitlabUser
import com.example.demo.repository.UserRepository
import com.example.demo.service.NotificationSenderService
import com.example.demo.service.UserService
import com.google.gson.Gson
import java.lang.IllegalArgumentException
import java.net.HttpURLConnection
import java.net.URL
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
        val userRepository: UserRepository
) : UserService {

    override fun registerByUsername(channelId: String, username: String) : GitlabUser {
        var user = getUserByUsername(username)
        if (user != null ) {
            val message = String.format("User with username %s already registered", username)
            throw IllegalArgumentException(message)
        }
        user = getFromApi(buildGitlabUsersUrl(username))
        user.channelId = channelId
        return userRepository.save(user)
    }

    override fun getUserByUsername(username: String): GitlabUser? {
        return userRepository.getByUsername(username)
    }

    override fun getUserById(id: String?): GitlabUser? {
        return userRepository.getById(id)
    }

    private fun getFromApi(url: URL): GitlabUser {
        var resp = ""
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    println(line)
                    resp += line
                }
            }
        }

        return Gson().fromJson(resp, Array<GitlabUser>::class.java)[0]
    }

    fun buildGitlabUsersUrl(username: String): URL {
        return URL(String.format("https://gitlab.com/api/v4/users?username=%s", username))
    }
}


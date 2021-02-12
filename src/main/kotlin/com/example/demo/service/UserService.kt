package com.example.demo.service

import com.example.demo.model.GitlabUser
import com.example.demo.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.IllegalArgumentException
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL
import org.springframework.stereotype.Service

@Service
class UserService(
        val userRepository: UserRepository
) {

    fun registerByUsername(channelId: String, username: String) {
        var user = getUserByUsername(username)
        if (user != null ) {
            throw IllegalArgumentException(String.format("User with username %s already registered", username))
        }
        user = getFromApi(buildGitlabUsersUrl(username))
        user.channelId = channelId
        userRepository.save(user)
    }

    fun getUserByUsername(username: String): GitlabUser? {
        return userRepository.getByUsername(username)
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


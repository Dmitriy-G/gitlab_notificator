package com.example.demo.service

import com.example.demo.model.GitlabUser
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import org.springframework.stereotype.Service

@Service
class UserService {

    fun getUserByUsername(username: String): GitlabUser {
        val url = URL(String.format("https://gitlab.com/api/v4/users?username=%s", username))
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

        return Gson().fromJson(resp, GitlabUser::class.java)
    }
}


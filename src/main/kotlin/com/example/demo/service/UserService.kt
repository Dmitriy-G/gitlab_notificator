package com.example.demo.service

import com.example.demo.model.GitlabUser

interface UserService {
    fun registerByUsername(channelId: String, username: String) : GitlabUser?
    fun getUserByUsername(username: String): GitlabUser?
    fun getUserById(id: String?): GitlabUser?
}

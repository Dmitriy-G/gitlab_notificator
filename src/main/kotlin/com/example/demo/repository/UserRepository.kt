package com.example.demo.repository

import com.example.demo.model.GitlabUser
import org.springframework.stereotype.Repository

interface UserRepository {
    fun save(user: GitlabUser): GitlabUser
    fun getByUsername(username: String): GitlabUser?
    fun getById(id: String?): GitlabUser?
    fun getByChannelId(channelId: String): GitlabUser?
}

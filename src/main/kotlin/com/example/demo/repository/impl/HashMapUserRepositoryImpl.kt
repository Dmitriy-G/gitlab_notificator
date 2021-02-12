package com.example.demo.repository.impl

import com.example.demo.model.GitlabUser
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Repository


@Repository
class HashMapRepositoryImpl : UserRepository{

    private val storage: MutableMap<String, GitlabUser> = mutableMapOf()


    override fun save(user: GitlabUser) {
        storage[user.id] = user
    }

    override fun getByUsername(username: String): GitlabUser? {
        return storage.entries.firstOrNull { it.value.username == username}?.value
    }

    override fun getById(id: String): GitlabUser? {
        return storage[id]
    }

    override fun getByChannelId(channelId: String): GitlabUser? {
        return storage.entries.firstOrNull { it.value.channelId == channelId}?.value
    }
}

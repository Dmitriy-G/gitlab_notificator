package com.example.demo.model

data class GitlabUser(
    val id: String,
    val name: String,
    val username: String,
    val state: String,
    var channelId: String
)

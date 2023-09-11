package com.example.test_message_platform.domain.message.dto

data class MessageTemplate(
    val deviceToken: String,
    val title: String,
    val content: String,
    val messageId: Long,
)
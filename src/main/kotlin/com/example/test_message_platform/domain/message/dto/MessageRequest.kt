package com.example.test_message_platform.domain.message.dto

import com.fasterxml.jackson.annotation.JsonTypeInfo

class MessageRequest(

) {
    data class SendMessage(
        val userDeviceToken: String,
        val title: String,
        val content: String,
    )
}
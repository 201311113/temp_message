package com.example.test_message_platform.domain.message.dto

import com.example.test_message_platform.common.utils.Response

class MessageResponse {
    data class SendMessage(
        val messageId: Long
    )

    data class GenerateMessageHistoryId(
        val messageId: Long
    )
}
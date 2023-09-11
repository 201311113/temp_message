package com.example.test_message_platform.domain.message.controller

import com.example.test_message_platform.common.provider.ServiceProvider
import com.example.test_message_platform.common.utils.Response
import com.example.test_message_platform.domain.message.dto.MessageRequest
import com.example.test_message_platform.domain.message.dto.MessageResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/message")
class MessageController (
    private val serviceProvider: ServiceProvider
){
    //알림 전송 api
    @PostMapping("/send")
    fun sendMessage(
        @RequestBody req: MessageRequest.SendMessage
    ): Mono<Response<MessageResponse.SendMessage>>{
        return serviceProvider.messageService.sendMessage(req)
    }

    //알리미에서 push에 보낼 ID 발급 api
    @GetMapping("/generate-unique-id")
    fun generateMessageId(): Mono<Response<MessageResponse.GenerateMessageHistoryId>>{
        return serviceProvider.messageService.generateMessageId()
    }
}
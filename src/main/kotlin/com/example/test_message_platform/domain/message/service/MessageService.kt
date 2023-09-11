package com.example.test_message_platform.domain.message.service

import com.example.test_message_platform.common.PubSubManager
import com.example.test_message_platform.common.provider.HelperProvider
import com.example.test_message_platform.common.provider.RepositoryProvider
import com.example.test_message_platform.common.provider.ServiceProvider
import com.example.test_message_platform.common.utils.Response
import com.example.test_message_platform.domain.message.dto.MessageRequest
import com.example.test_message_platform.domain.message.dto.MessageResponse
import com.example.test_message_platform.domain.message.dto.MessageTemplate
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

private val log = KotlinLogging.logger{}
@Service
class MessageService(
    private val repo: RepositoryProvider,
    private val pubSubHelper: PubSubManager,
    @Value("\${message-queue-name}") val queueName: String
) {

    fun sendMessage(req: MessageRequest.SendMessage): Mono<Response<MessageResponse.SendMessage>>{
        return repo.redisRepository.increment("messageUniqueId")
            .flatMap { messageId ->
                val messageTemplate = convertToMessageTemplate(req, messageId)

                pubSubHelper.produceMessageToRedis(queueName, messageTemplate)

                Mono.just(Response(data = MessageResponse.SendMessage(messageId = messageId!!)))
            }
    }

    fun generateMessageId(): Mono<Response<MessageResponse.GenerateMessageHistoryId>>{
        return repo.redisRepository.increment("messageUniqueId")
            .map { incrementValue -> Response(data = MessageResponse.GenerateMessageHistoryId(incrementValue)) }
    }

    private fun convertToMessageTemplate(
        req: MessageRequest.SendMessage,
        messageId: Long
    ): MessageTemplate {
        return MessageTemplate(
            deviceToken = req.userDeviceToken,
            title = req.title,
            content = req.content,
            messageId = messageId
        )
    }
}
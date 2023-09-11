package com.example.test_message_platform.common

import com.example.test_message_platform.common.provider.HelperProvider
import com.example.test_message_platform.domain.message.dto.MessageTemplate
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

private val log = KotlinLogging.logger{}
private val objectMapper = ObjectMapper()
@Component
class PubSubManager(
    private val reactiveRedisTemplate: ReactiveStringRedisTemplate,
    private val helpers: HelperProvider,
    @Value("\${message-queue-name}") val queueName: String
) {

    @PostConstruct
    fun consumeMessageFromRedis() {
        reactiveRedisTemplate.listenTo(ChannelTopic(queueName))
            .map { it.message }
            .doOnNext { message ->
                log.info("message: ${message}")
            }
            .subscribe()
    }

    fun produceMessageToRedis(key: String, messageTemplate: MessageTemplate){
        val messageAsJson = objectMapper.writeValueAsString(messageTemplate)
        reactiveRedisTemplate.convertAndSend(key, messageAsJson)
            .subscribe{ result ->
                log.info { "Published $messageAsJson to channel $key. Result: $result" }
            }
    }
}
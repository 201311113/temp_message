package com.example.test_message_platform.common.database

import com.example.test_message_platform.common.utils.EMPTY_MESSAGE
import com.example.test_message_platform.domain.message.dto.MessageTemplate
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit

private val objectMapper = ObjectMapper()
@Repository
class RedisRepository(
//    private val redisTemplate: StringRedisTemplate,
    private val reactiveRedisTemplate: ReactiveStringRedisTemplate
) {
    fun increment(key: String): Mono<Long> {
        return reactiveRedisTemplate.opsForValue().increment(key)
    }

    fun enqueue(key: String, data: Any): Mono<Long> {
        return reactiveRedisTemplate.opsForList().leftPush(key, convertToJson(data))
    }

    fun <T> dequeue(key: String, type: Class<T>): Mono<T?> {
        return reactiveRedisTemplate.opsForList().rightPop(key)
            .map { it?.let { convertToDto(it, type) } }
    }

    private fun convertToJson(data: Any): String {
        return objectMapper.writeValueAsString(data)
    }

    private fun <T> convertToDto(value: String, type: Class<T>): T {
        return objectMapper.readValue(value, type)
    }
}
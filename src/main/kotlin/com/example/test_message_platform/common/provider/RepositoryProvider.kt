package com.example.test_message_platform.common.provider

import com.example.test_message_platform.common.database.RedisRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RepositoryProvider {
    @Autowired
    lateinit var redisRepository: RedisRepository
}
package com.example.test_message_platform.common.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching
class RedisConfig(
    @Value("\${spring.redis.host}") val redisHost: String = "localhost",
    @Value("\${spring.redis.port}") val redisPort: Int = 6379,
    @Value("\${spring.redis.password}") val redisPassword: String? = null,
) {
    @Autowired
    lateinit var environment: Environment

    @Bean
    @Primary
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        val profiles = environment!!.activeProfiles
        return if (profiles.contains("local")){
            LettuceConnectionFactory(redisHost, redisPort)
        }else {
            val config = LettuceClientConfiguration.builder()
                .useSsl().disablePeerVerification().build()

            LettuceConnectionFactory(
                RedisStandaloneConfiguration(redisHost!!, redisPort).apply { password = RedisPassword.of(redisPassword) },
                config
            )
        }
    }

//    @Bean
//    fun redisConnectionFactory(): RedisConnectionFactory {
//        val profiles = environment!!.activeProfiles
//        return if (profiles.contains("local")){
//            LettuceConnectionFactory(redisHost, redisPort)
//        }else {
//            val config= LettuceClientConfiguration.builder()
//                .useSsl().disablePeerVerification().build()
//
//            LettuceConnectionFactory(
//                RedisStandaloneConfiguration(redisHost!!, redisPort).apply { password =
//                    RedisPassword.of(redisPassword) },
//                config
//            )
//        }
//    }

    @Bean
    fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory): ReactiveStringRedisTemplate {
        val serializer = StringRedisSerializer()
        val serializationContext = RedisSerializationContext.newSerializationContext<String, String>()
            .key(serializer)
            .value(serializer)
            .hashKey(serializer)
            .hashValue(serializer)
            .build()

        return ReactiveStringRedisTemplate(factory, serializationContext)
    }

//    @Bean
//    fun redisTemplate(factory: RedisConnectionFactory): StringRedisTemplate {
//        val template = StringRedisTemplate()
//        template.connectionFactory = factory
//
//        return template;
//    }
}
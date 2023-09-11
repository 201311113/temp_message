package com.example.test_message_platform.common

import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.UUID


private val log = KotlinLogging.logger{}
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class HttpLoggingFilter: WebFilter{
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        // request data
        val url = exchange.request.uri
        val requestHeader = exchange.request.headers
        val apiMethod = exchange.request.method
        val clientIp = exchange.request.remoteAddress
        // response data
        val responseHttpStatus = exchange.response.statusCode
        val responseHeader = exchange.request.headers
        val requestId = UUID.randomUUID().toString()

        MDC.put("requestId", "requestID : " + requestId)
        MDC.put("url", "url : " + url)
        MDC.put("clientIp", "clientIp : " + clientIp)
        MDC.put("headers", responseHeader.toString())
        MDC.put("headers", responseHeader.toString())
        log.info { "log" }
        return chain.filter(exchange)
    }

}
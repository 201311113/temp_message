//package com.example.test_message_platform.common
//
//import org.springframework.core.Ordered
//import org.springframework.core.annotation.Order
//import org.springframework.http.HttpStatus
//import org.springframework.stereotype.Component
//import org.springframework.web.server.ServerWebExchange
//import org.springframework.web.server.WebExceptionHandler
//import reactor.core.publisher.Mono
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//class ExceptionHandler: WebExceptionHandler {
//    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
//        val response = exchange.response
//        val status: HttpStatus = when (e) {
//
//        }
//    }
//}
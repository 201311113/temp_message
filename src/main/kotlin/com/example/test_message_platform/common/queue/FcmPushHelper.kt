package com.example.test_message_platform.common.queue

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class FcmPushHelper {
    init {
        // Firebase Admin SDK 초기화
        val serviceAccount = ClassPathResource("firebase/serviceAccountKey.json").inputStream
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()
        FirebaseApp.initializeApp(options)
    }

    fun sendPushNotification(deviceToken: String, title: String, message: String): Mono<String> {
        return try {
            // FCM 메시지 생성
            val fcmMessage = Message.builder()
                .setNotification(
                    Notification.builder()
                        .setTitle(title)
                        .setBody(message)
                        .build()
                )
                .setToken(deviceToken)
                .build()

            // FCM 메시지 전송
            val response = FirebaseMessaging.getInstance().send(fcmMessage)

            Mono.just("FCM 메시지 전송 성공: $response")
        } catch (e: Exception) {
            Mono.error(e)
        }
    }
}
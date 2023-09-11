package com.example.test_message_platform.common.provider

//import com.example.test_message_platform.common.service.FcmPushService
import com.example.test_message_platform.domain.message.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ServiceProvider {
    @Autowired
    lateinit var messageService: MessageService
//    lateinit var fcmPushService: FcmPushService
}
package com.example.test_message_platform.common.provider

import com.example.test_message_platform.common.queue.FcmPushHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class HelperProvider {
    @Autowired
    lateinit var fcmPushHelper: FcmPushHelper
//    @Autowired
//    lateinit var messageQueueHelper: MessageQueueHelper
}
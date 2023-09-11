package com.example.test_message_platform.common.utils

data class Response<T>(
    val code: Int = SUCCESS_CODE,
    val message: String = EMPTY_MESSAGE,
    val data: T? = null,
) {
    constructor(errorCode: ErrorCode, data: T? = null): this(code = errorCode.code, message = errorCode.message, data = data)
}
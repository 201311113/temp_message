package com.example.test_message_platform.common.utils


const val INVALID_SERVER_ERROR_CODE = 5000
enum class ErrorCode(
    val code: Int,
    val message: String = EMPTY_MESSAGE
) {
    UNKNOWN_ERROR(INVALID_SERVER_ERROR_CODE, "알 수 없는 오류입니다. 다시 시도해주세요")
}
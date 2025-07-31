package com.omnistore.omnistore_user_service.infrastructure.exception

data class ErrorResponseDto (
    val status: Int,
    val message: String?,
    val path: String,
    val timestamp: String
)
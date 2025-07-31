package com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto

data class UserRequestDto(
    val email: String,
    val name: String,
    val password: String,
)

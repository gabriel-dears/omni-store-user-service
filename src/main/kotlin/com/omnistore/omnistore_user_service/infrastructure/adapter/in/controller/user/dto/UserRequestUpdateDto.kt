package com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserRequestUpdateDto(
    @field:Email
    @field:Size(max = 255, min = 20)
    @field:NotBlank
    val email: String,

    @field:NotBlank
    @field:Size(max = 255)
    val name: String,

    @field:Pattern(regexp = "ADMIN|CUSTOMER|MANAGER", message = "Role must be one of: ADMIN, CUSTOMER, MANAGER")
    val role: String
)

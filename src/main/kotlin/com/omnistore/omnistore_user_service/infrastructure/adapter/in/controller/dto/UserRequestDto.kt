package com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserRequestDto(
    @Email
    @Size(max = 255, min = 20)
    @NotBlank
    val email: String,
    @NotBlank
    @Size(max = 255)
    val name: String,
    @NotBlank
    @Size(min = 30, max = 255)
    val password: String,
    @Pattern(regexp = "ADMIN|CUSTOMER|MANAGER", message = "Role must be one of: ADMIN, CUSTOMER, MANAGER")
    val role: String
)

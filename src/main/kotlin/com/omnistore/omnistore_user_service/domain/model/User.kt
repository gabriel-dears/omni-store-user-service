package com.omnistore.omnistore_user_service.domain.model

import java.time.Instant
import java.util.UUID

data class User(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val passwordHash: String,
    val enabled: Boolean = true,
    val role: Role,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)

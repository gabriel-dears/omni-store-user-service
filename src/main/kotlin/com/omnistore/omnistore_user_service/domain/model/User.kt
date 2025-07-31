package com.omnistore.omnistore_user_service.domain.model

import java.time.Instant
import java.util.UUID

data class User(
    val id: UUID,
    var name: String,
    var email: String,
    var passwordHash: String,
    var enabled: Boolean,
    var role: Role,
    var createdAt: Instant,
    var updatedAt: Instant
)

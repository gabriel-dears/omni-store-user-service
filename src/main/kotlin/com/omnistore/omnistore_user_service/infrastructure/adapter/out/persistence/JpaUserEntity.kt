package com.omnistore.omnistore_user_service.infrastructure.adapter.out.persistence

import java.time.Instant
import java.util.*

data class JpaUserEntity(
    val id: UUID,
    var email: String,
    var name: String,
    var passwordHash: String,
    var enabled: Boolean,
    var role: String,
    var createdAt: Instant,
    var updatedAt: Instant
)
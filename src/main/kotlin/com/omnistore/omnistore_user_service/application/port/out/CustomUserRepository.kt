package com.omnistore.omnistore_user_service.application.port.out

import com.omnistore.omnistore_user_service.domain.model.User
import java.util.*

interface CustomUserRepository {
    fun findById(id: UUID): User?
    fun createUser(user: User): User
    fun existByEmail(email: String): Boolean
}
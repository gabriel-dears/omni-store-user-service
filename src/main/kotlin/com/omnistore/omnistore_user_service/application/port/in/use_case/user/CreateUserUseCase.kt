package com.omnistore.omnistore_user_service.application.port.`in`.use_case.user

import com.omnistore.omnistore_user_service.domain.model.User

interface CreateUserUseCase {
    fun execute(user: User): User
}
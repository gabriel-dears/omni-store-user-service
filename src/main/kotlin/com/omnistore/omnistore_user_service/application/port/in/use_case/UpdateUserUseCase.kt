package com.omnistore.omnistore_user_service.application.port.`in`.use_case

import com.omnistore.omnistore_user_service.domain.model.User

interface UpdateUserUseCase {
    fun execute(user: User): User
}
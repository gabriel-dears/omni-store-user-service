package com.omnistore.omnistore_user_service.application.port.`in`.use_case

import java.util.UUID

interface DeleteUserUseCase {
    fun execute(id: UUID)
}
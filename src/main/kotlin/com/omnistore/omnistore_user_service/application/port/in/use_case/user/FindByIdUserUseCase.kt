package com.omnistore.omnistore_user_service.application.port.`in`.use_case.user

import com.omnistore.omnistore_user_service.domain.model.User
import java.util.UUID

interface FindByIdUserUseCase {
    fun execute(id: UUID): User
}
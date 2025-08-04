package com.omnistore.omnistore_user_service.application.service.user

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.DeleteUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
import java.util.UUID

class DeleteUserUseCaseImpl(private val customUserRepository: CustomUserRepository) : DeleteUserUseCase {
    override fun execute(id: UUID) {
        if (!customUserRepository.existById(id)) {
            throw UserNotFoundException("User with id $id not found")
        }
        customUserRepository.delete(id)
    }
}
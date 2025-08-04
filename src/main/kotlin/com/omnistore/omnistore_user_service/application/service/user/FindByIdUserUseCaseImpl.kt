package com.omnistore.omnistore_user_service.application.service.user

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.FindByIdUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
import com.omnistore.omnistore_user_service.domain.model.User
import java.util.UUID

class FindByIdUserUseCaseImpl(private val customUserRepository: CustomUserRepository) : FindByIdUserUseCase {
    override fun execute(id: UUID): User {
        val user = customUserRepository.findById(id)
        if (user == null) {
            throw UserNotFoundException("User with id $id not found")
        }
        return user
    }
}
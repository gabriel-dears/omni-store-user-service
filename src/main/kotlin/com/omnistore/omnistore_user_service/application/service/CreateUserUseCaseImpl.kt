package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.model.User

class CreateUserUseCaseImpl(val customUserRepository: CustomUserRepository) : CreateUserUseCase {
    override fun execute(user: User): User {
        return customUserRepository.createUser(user)
    }
}
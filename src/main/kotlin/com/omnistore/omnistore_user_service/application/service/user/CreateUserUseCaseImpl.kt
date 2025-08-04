package com.omnistore.omnistore_user_service.application.service.user

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.EmailAlreadyExistsException
import com.omnistore.omnistore_user_service.domain.model.User

class CreateUserUseCaseImpl(private val customUserRepository: CustomUserRepository) : CreateUserUseCase {
    override fun execute(user: User): User {
        validateUser(user)
        return customUserRepository.createUser(user)
    }

    private fun validateUser(user: User) {
        if (customUserRepository.existByEmail(user.email)) throw EmailAlreadyExistsException("E-mail ${user.email} already exists")
    }

}
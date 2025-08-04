package com.omnistore.omnistore_user_service.application.service.user

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.user.UpdateUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
import com.omnistore.omnistore_user_service.domain.model.User

class UpdateUserUseCaseImpl(private val customUserRepository: CustomUserRepository) : UpdateUserUseCase {
    override fun execute(
        user: User
    ): User {
        if (!customUserRepository.existById(user.id!!)) {
            throw UserNotFoundException("User with id ${user.id} not found")
        }
        customUserRepository.updateUser(user)
        return customUserRepository.findById(user.id)!!
    }
}
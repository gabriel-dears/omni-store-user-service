package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.DeleteUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository

class DeleteUserUseCaseImpl(private val customUserRepository: CustomUserRepository) : DeleteUserUseCase {
    override fun execute(id: String) {
        TODO("Not yet implemented")
    }
}
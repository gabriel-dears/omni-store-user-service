package com.omnistore.omnistore_user_service.infrastructure.config.bean

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.DeleteUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.FindByIdUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.UpdateUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.application.service.CreateUserUseCaseImpl
import com.omnistore.omnistore_user_service.application.service.DeleteUserUseCaseImpl
import com.omnistore.omnistore_user_service.application.service.FindByIdUserUseCaseImpl
import com.omnistore.omnistore_user_service.application.service.UpdateUserUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserBeanConfig {

    @Bean
    fun createUserUseCase(customUserRepository: CustomUserRepository): CreateUserUseCase {
        return CreateUserUseCaseImpl(customUserRepository)
    }

    @Bean
    fun findByIdUserUseCase(customUserRepository: CustomUserRepository): FindByIdUserUseCase {
        return FindByIdUserUseCaseImpl(customUserRepository)
    }

    @Bean
    fun deleteUserUseCase(customUserRepository: CustomUserRepository): DeleteUserUseCase {
        return DeleteUserUseCaseImpl(customUserRepository)
    }

    @Bean
    fun updateUserUseCase(customUserRepository: CustomUserRepository): UpdateUserUseCase {
        return UpdateUserUseCaseImpl(customUserRepository)
    }

}
package com.omnistore.omnistore_user_service.infrastructure.config.bean

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.FindByIdUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.application.service.CreateUserUseCaseImpl
import com.omnistore.omnistore_user_service.application.service.FindByIdUserUseCaseImpl
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

}
package com.omnistore.omnistore_user_service.infrastructure.config.bean.user

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.user.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.user.DeleteUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.user.FindByIdUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.user.UpdateUserUseCase
import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.application.service.user.CreateUserUseCaseImpl
import com.omnistore.omnistore_user_service.application.service.user.DeleteUserUseCaseImpl
import com.omnistore.omnistore_user_service.application.service.user.FindByIdUserUseCaseImpl
import com.omnistore.omnistore_user_service.application.service.user.UpdateUserUseCaseImpl
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
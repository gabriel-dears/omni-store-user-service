package com.omnistore.omnistore_user_service.infrastructure.mapper

import com.omnistore.omnistore_user_service.domain.model.User
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserResponseDto


object DtoUserMapper {

    fun toUserResponseDto(user: User): UserResponseDto {
        return UserResponseDto(
            user.id.toString(),
            user.email,
            user.name,
        )
    }

}
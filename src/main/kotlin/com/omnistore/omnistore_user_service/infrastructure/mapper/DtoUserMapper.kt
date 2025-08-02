package com.omnistore.omnistore_user_service.infrastructure.mapper

import com.omnistore.omnistore_user_service.domain.model.Role
import com.omnistore.omnistore_user_service.domain.model.User
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserRequestDto
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserRequestUpdateDto
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserResponseDto
import java.util.UUID


object DtoUserMapper {

    fun toUserResponseDto(user: User): UserResponseDto {
        return UserResponseDto(
            id = user.id.toString(),
            email = user.email,
            name = user.name
        )
    }

    fun toUser(userRequestDto: UserRequestDto): User {
        return User(
            name = userRequestDto.name,
            email = userRequestDto.email,
            enabled = true,
            role = Role.CUSTOMER,
            passwordHash = userRequestDto.password
        )
    }

    fun toUser(userRequestUpdateDto: UserRequestUpdateDto, id: UUID): User {



        return User(
            id = id,
            name = userRequestUpdateDto.name,
            email = userRequestUpdateDto.email,
            enabled = true,
            role = Role.CUSTOMER,
            passwordHash = ""
        )
    }

}
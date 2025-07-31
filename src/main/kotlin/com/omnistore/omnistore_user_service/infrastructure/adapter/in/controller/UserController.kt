package com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.FindByIdUserUseCase
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserRequestDto
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserResponseDto
import com.omnistore.omnistore_user_service.infrastructure.mapper.DtoUserMapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("users")
class UserController(
    val createUserUseCase: CreateUserUseCase,
    val findByIdUserUseCase: FindByIdUserUseCase
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<UserResponseDto> {
        val userAfterRetrieving = findByIdUserUseCase.execute(id)
        val userResponseDto = DtoUserMapper.toUserResponseDto(userAfterRetrieving)
        return ResponseEntity.ok(userResponseDto)
    }

    @PostMapping
    fun createUser(@Valid @RequestBody userRequestDto: UserRequestDto): ResponseEntity<UserResponseDto> {
        val userResponseAfterCreation = DtoUserMapper.toUser(userRequestDto)
        val createdUser = createUserUseCase.execute(userResponseAfterCreation)
        val userResponseDto = DtoUserMapper.toUserResponseDto(createdUser)
        return ResponseEntity.ok(userResponseDto)
    }

}
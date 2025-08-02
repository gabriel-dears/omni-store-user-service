package com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.DeleteUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.FindByIdUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.UpdateUserUseCase
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserRequestDto
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserRequestUpdateDto
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.dto.UserResponseDto
import com.omnistore.omnistore_user_service.infrastructure.mapper.DtoUserMapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("users")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val findByIdUserUseCase: FindByIdUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<UserResponseDto> {
        val userAfterRetrieving = findByIdUserUseCase.execute(id)
        val userResponseDto = DtoUserMapper.toUserResponseDto(userAfterRetrieving)
        return ResponseEntity.ok(userResponseDto)
    }

    @PostMapping
    fun createUser(@RequestBody @Valid userRequestDto: UserRequestDto): ResponseEntity<UserResponseDto> {
        val userResponseAfterCreation = DtoUserMapper.toUser(userRequestDto)
        val createdUser = createUserUseCase.execute(userResponseAfterCreation)
        val userResponseDto = DtoUserMapper.toUserResponseDto(createdUser)
        return ResponseEntity.ok(userResponseDto)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<Unit> {
        deleteUserUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun updateUser(
        @RequestBody @Valid userRequestUpdateDto: UserRequestUpdateDto,
        @PathVariable id: UUID
    ): ResponseEntity<UserResponseDto> {
        val userResponseAfterCreation = DtoUserMapper.toUser(userRequestUpdateDto, id)
        val updatedUser = updateUserUseCase.execute(userResponseAfterCreation)
        val userResponseDto = DtoUserMapper.toUserResponseDto(updatedUser)
        return ResponseEntity.ok(userResponseDto)
    }

}
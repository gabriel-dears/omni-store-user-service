package com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.user

import com.omnistore.omnistore_user_service.application.port.`in`.use_case.CreateUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.DeleteUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.FindByIdUserUseCase
import com.omnistore.omnistore_user_service.application.port.`in`.use_case.UpdateUserUseCase
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.user.dto.UserRequestDto
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.user.dto.UserRequestUpdateDto
import com.omnistore.omnistore_user_service.infrastructure.adapter.`in`.controller.user.dto.UserResponseDto
import com.omnistore.omnistore_user_service.infrastructure.mapper.DtoUserMapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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
package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.EmailAlreadyExistsException
import com.omnistore.omnistore_user_service.domain.model.Role
import com.omnistore.omnistore_user_service.domain.model.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class CreateUserUseCaseImplTest {

    @Mock
    private lateinit var customUserRepository: CustomUserRepository

    @InjectMocks
    private lateinit var createUserUseCaseImpl: CreateUserUseCaseImpl

    @Test
    fun `should fail when email already exists`() {
        // Arrange
        `when`(customUserRepository.existByEmail(any())).thenReturn(true)
        // Act and Assert
        assertThrows<EmailAlreadyExistsException> { createUserUseCaseImpl.execute(getUser()) }
    }

    @Test
    fun `should create user`() {
        // Arrange
        val user = getUser()
        `when`(customUserRepository.createUser(any())).thenReturn(user)
        // Act
        val createdUser = createUserUseCaseImpl.execute(user)
        // Assert
        assertEquals(createdUser.email, user.email)
        assertEquals(createdUser.name, user.name)
        assertEquals(createdUser.enabled, user.enabled)
        assertEquals(createdUser.role, user.role)
    }

    private fun getUser(): User {
        return User(
            name = "Test User",
            email = "test",
            passwordHash = "<PASSWORD>",
            enabled = true,
            role = Role.CUSTOMER
        )
    }

}
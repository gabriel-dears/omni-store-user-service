package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.application.service.user.CreateUserUseCaseImpl
import com.omnistore.omnistore_user_service.domain.exception.EmailAlreadyExistsException
import com.omnistore.omnistore_user_service.domain.model.Role
import com.omnistore.omnistore_user_service.domain.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class CreateUserUseCaseImplTest {

    @Mock
    lateinit var customUserRepository: CustomUserRepository

    lateinit var createUserUseCaseImpl: CreateUserUseCaseImpl

    @BeforeEach
    fun setUp() {
        createUserUseCaseImpl = CreateUserUseCaseImpl(customUserRepository)
    }

    @Test
    fun `should fail when email already exists`() {
        // Arrange
        whenever(customUserRepository.existByEmail(any())).thenReturn(true)

        // Act and Assert
        assertThrows<EmailAlreadyExistsException> {
            createUserUseCaseImpl.execute(getUser())
        }
    }

    @Test
    fun `should create user`() {
        // Arrange
        val user = getUser()
        whenever(customUserRepository.createUser(any())).thenReturn(user)

        // Act
        val createdUser = createUserUseCaseImpl.execute(user)

        // Assert
        assertEquals(user.email, createdUser.email)
        assertEquals(user.name, createdUser.name)
        assertEquals(user.enabled, createdUser.enabled)
        assertEquals(user.role, createdUser.role)
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

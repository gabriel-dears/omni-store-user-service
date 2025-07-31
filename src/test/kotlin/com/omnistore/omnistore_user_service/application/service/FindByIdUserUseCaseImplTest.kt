package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
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
import java.time.Instant
import java.util.*

@ExtendWith(MockitoExtension::class)
class FindByIdUserUseCaseImplTest {

    @Mock
    private lateinit var customUserRepository: CustomUserRepository

    @InjectMocks
    private lateinit var findByIdUserUseCaseImpl: FindByIdUserUseCaseImpl

    @Test
    fun `should fail when unknown id is provided`() {
        // Arrange
        `when`(customUserRepository.findById(any())).thenReturn(null)
        // Act and Assert
        assertThrows<UserNotFoundException> { findByIdUserUseCaseImpl.execute(any()) }
        findByIdUserUseCaseImpl.execute(any())
    }

    @Test
    fun `should find user when id is provided`() {
        // Arrange
        val id = UUID.randomUUID()
        `when`(customUserRepository.findById(any())).thenReturn(
            User(
                id,
                "<EMAIL>",
                "Test User",
                "",
                true,
                Role.CUSTOMER,
                Instant.now(),
                Instant.now()
            )
        )
        // Act
        val fetchedUser = findByIdUserUseCaseImpl.execute(any())
        // Assert
        assert(fetchedUser.id == id)
        assert(fetchedUser.email == "<EMAIL>")
        assert(fetchedUser.name == "Test User")
        assert(fetchedUser.passwordHash == "")
        assert(fetchedUser.enabled)
        assert(fetchedUser.role == Role.CUSTOMER)
    }

}
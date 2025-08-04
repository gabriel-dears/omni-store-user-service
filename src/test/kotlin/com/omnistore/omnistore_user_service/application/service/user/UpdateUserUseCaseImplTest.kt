package com.omnistore.omnistore_user_service.application.service.user

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
import com.omnistore.omnistore_user_service.domain.model.Role
import com.omnistore.omnistore_user_service.domain.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

@ExtendWith(MockitoExtension::class)
class UpdateUserUseCaseImplTest {

    @Mock
    private lateinit var customUserRepository: CustomUserRepository

    private lateinit var updateUserUseCaseImpl: UpdateUserUseCaseImpl

    @BeforeEach
    fun setUp() {
        updateUserUseCaseImpl = UpdateUserUseCaseImpl(customUserRepository)
    }

    @Test
    fun `should not update user with unknown id`() {
        // Arrange
        whenever(customUserRepository.existById(any())).thenReturn(false)
        // Act and Assert
        assertThrows<UserNotFoundException> {
            updateUserUseCaseImpl.execute(
                User(
                    UUID.randomUUID(),
                    "<EMAIL>",
                    "Test User",
                    "",
                    true,
                    Role.CUSTOMER,
                    null,
                    null
                )
            )
        }
        verify(customUserRepository, times(1)).existById(any())
    }

    @Test
    fun `should update user with unknown id`() {
        // Arrange
        whenever(customUserRepository.existById(any())).thenReturn(true)
        whenever(customUserRepository.findById(any())).thenReturn(
            User(
                UUID.randomUUID(),
                "<EMAIL>",
                "Test User",
                "",
                true,
                Role.CUSTOMER,
                null,
                null
            )
        )
        // Act
        val updatedUser = updateUserUseCaseImpl.execute(
            User(
                UUID.randomUUID(),
                "<EMAIL>",
                "Test User",
                "",
                true,
                Role.CUSTOMER,
                null,
                null
            )
        )
        // Assert
        assertNotNull(updatedUser)
    }

}
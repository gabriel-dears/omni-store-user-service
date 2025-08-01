package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
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
import java.time.Instant
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class FindByIdUserUseCaseImplTest {

    @Mock
    lateinit var customUserRepository: CustomUserRepository

    lateinit var findByIdUserUseCaseImpl: FindByIdUserUseCaseImpl

    @BeforeEach
    fun setUp() {
        findByIdUserUseCaseImpl = FindByIdUserUseCaseImpl(customUserRepository)
    }

    @Test
    fun `should fail when unknown id is provided`() {
        // Arrange
        whenever(customUserRepository.findById(any())).thenReturn(null)
        // Act and Assert
        assertThrows<UserNotFoundException> { findByIdUserUseCaseImpl.execute(UUID.randomUUID()) }
    }

    @Test
    fun `should find user when id is provided`() {
        // Arrange
        val id = UUID.randomUUID()
        val creationInstant = Instant.now()
        whenever(customUserRepository.findById(any())).thenReturn(
            User(
                id = id,
                email = "<EMAIL>",
                name = "Test User",
                passwordHash = "",
                enabled = true,
                role = Role.CUSTOMER,
                createdAt = creationInstant,
                updatedAt = creationInstant
            )
        )
        // Act
        val fetchedUser = findByIdUserUseCaseImpl.execute(id)
        // Assert
        assertEquals(id, fetchedUser.id)
        assertEquals("<EMAIL>", fetchedUser.email)
        assertEquals("Test User", fetchedUser.name)
        assertTrue { fetchedUser.enabled }
        assertEquals(Role.CUSTOMER, fetchedUser.role)
    }

}
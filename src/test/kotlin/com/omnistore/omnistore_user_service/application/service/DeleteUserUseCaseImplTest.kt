package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserNotFoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class DeleteUserUseCaseImplTest {

    @Mock
    lateinit var customUserRepository: CustomUserRepository

    lateinit var deleteUserUseCaseImpl: DeleteUserUseCaseImpl

    @BeforeEach
    fun setUp() {
        deleteUserUseCaseImpl = DeleteUserUseCaseImpl(customUserRepository)
    }

    @Test
    fun `should not delete when unknown id is provided`() {
        // Arrange
        val uuid = UUID.randomUUID()
        val existsById = false
        whenever(customUserRepository.existById(id = any())).thenReturn(existsById)
        // Act and Assert
        assertThrows<UserNotFoundException> { deleteUserUseCaseImpl.execute(uuid) }
        verify(customUserRepository, times(1)).existById(id = uuid)
        verify(customUserRepository, times(0)).delete(id = uuid)
    }

    @Test
    fun `should delete when known id is provided`() {
        // Arrange
        val uuid = UUID.randomUUID()
        val existsById = true
        whenever(customUserRepository.existById(id = any())).thenReturn(existsById)
        // Act
        deleteUserUseCaseImpl.execute(id = uuid)
        // Assert
        verify(customUserRepository, times(1)).existById(id = uuid)
        verify(customUserRepository, times(1)).delete(id = uuid)
    }

}
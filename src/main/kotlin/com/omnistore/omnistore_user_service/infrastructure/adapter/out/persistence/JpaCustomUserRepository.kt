package com.omnistore.omnistore_user_service.infrastructure.adapter.out.persistence

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.application.service.database.UserDatabaseInteractionHandler
import com.omnistore.omnistore_user_service.domain.model.User
import com.omnistore.omnistore_user_service.infrastructure.mapper.JpaUserMapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
class JpaCustomUserRepository(
    val jpaUserRepository: JpaUserRepository
) : CustomUserRepository {

    override fun findById(id: UUID): User? {
        return UserDatabaseInteractionHandler.handleDatabaseInteraction {
            jpaUserRepository
                .findById(id)
                .map { JpaUserMapper.toDomain(it) }
                .orElse(null)
        }
    }

    override fun createUser(user: User): User {
        return UserDatabaseInteractionHandler.handleDatabaseInteraction {
            val jpaUserEntity = JpaUserMapper.toEntity(user)
            val createdJpaUserEntity = jpaUserRepository.save(jpaUserEntity)
            JpaUserMapper.toDomain(createdJpaUserEntity)
        }
    }

    override fun existByEmail(email: String): Boolean {
        return UserDatabaseInteractionHandler.handleDatabaseInteraction {
            jpaUserRepository.existsByEmail(email)
        }
    }

    override fun existById(id: UUID): Boolean {
        return UserDatabaseInteractionHandler.handleDatabaseInteraction {
            jpaUserRepository.existsById(id)
        }
    }

    override fun delete(id: UUID) {
        UserDatabaseInteractionHandler.handleDatabaseInteraction {
            jpaUserRepository.deleteById(id)
        }
    }

    @Transactional
    override fun updateUser(user: User) {
        UserDatabaseInteractionHandler.handleDatabaseInteraction {
            jpaUserRepository.updateUserWithoutPassword(user.id!!, user.name, user.email, user.role)
        }
    }


}
package com.omnistore.omnistore_user_service.infrastructure.adapter.out.persistence

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.model.User
import com.omnistore.omnistore_user_service.infrastructure.mapper.JpaUserMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class JpaCustomUserRepository(
    val jpaUserRepository: JpaUserRepository
) : CustomUserRepository {

    override fun findById(id: UUID): User? {
        return jpaUserRepository
            .findById(id)
            .map { JpaUserMapper.toDomain(it) }
            .orElse(null)
    }

    override fun createUser(user: User): User {
        val jpaUserEntity = JpaUserMapper.toEntity(user)
        val createdJpaUserEntity = jpaUserRepository.save(jpaUserEntity)
        return JpaUserMapper.toDomain(createdJpaUserEntity)
    }

}
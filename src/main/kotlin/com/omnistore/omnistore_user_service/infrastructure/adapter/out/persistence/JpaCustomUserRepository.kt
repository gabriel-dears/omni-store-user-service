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
        return jpaUserRepository.findById(id).map { JpaUserMapper.toDomain(it) }.orElse(null)
    }

    override fun createUser(user: User): User {
        val savedUserEntity = JpaUserMapper.toEntity(user).let { jpaUserRepository.save(it) }
        return JpaUserMapper.toDomain(savedUserEntity)
    }

}
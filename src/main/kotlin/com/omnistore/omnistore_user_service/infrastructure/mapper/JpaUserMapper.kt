package com.omnistore.omnistore_user_service.infrastructure.mapper

import com.omnistore.omnistore_user_service.domain.model.User
import com.omnistore.omnistore_user_service.infrastructure.adapter.out.persistence.JpaUserEntity

object JpaUserMapper {
    fun toEntity(user: User): JpaUserEntity {
        return JpaUserEntity(
            user.id,
            user.email,
            user.name,
            user.passwordHash,
            user.enabled,
            user.role,
            user.createdAt,
            user.updatedAt
        )
    }

    fun toDomain(jpaUserEntity: JpaUserEntity): User {
        return User(
            jpaUserEntity.id,
            jpaUserEntity.email,
            jpaUserEntity.name,
            jpaUserEntity.passwordHash,
            jpaUserEntity.enabled,
            jpaUserEntity.role,
            jpaUserEntity.createdAt,
            jpaUserEntity.updatedAt
        )
    }

}
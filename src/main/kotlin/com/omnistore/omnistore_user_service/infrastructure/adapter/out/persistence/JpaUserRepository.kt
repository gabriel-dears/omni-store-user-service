package com.omnistore.omnistore_user_service.infrastructure.adapter.out.persistence

import com.omnistore.omnistore_user_service.domain.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JpaUserRepository : JpaRepository<JpaUserEntity, UUID> {
    fun existsByEmail(email: String): Boolean

    @Modifying
    @Query("UPDATE JpaUserEntity u SET u.name = :name, u.email = :email, u.role = :role WHERE u.id = :id")
    fun updateUserWithoutPassword(id: UUID, name: String, email: String, role: Role)


}
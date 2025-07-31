package com.omnistore.omnistore_user_service.infrastructure.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface JpaUserRepository : JpaRepository<JpaUserEntity, UUID> {
}
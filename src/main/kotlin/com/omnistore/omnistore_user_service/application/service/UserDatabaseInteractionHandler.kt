package com.omnistore.omnistore_user_service.application.service

import com.omnistore.omnistore_user_service.application.port.out.CustomUserRepository
import com.omnistore.omnistore_user_service.domain.exception.UserDatabaseException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object UserDatabaseInteractionHandler {

    private val logger: Logger = LoggerFactory.getLogger(CustomUserRepository::class.java)

    fun <T> handleDatabaseInteraction(interactionCallback: () -> T): T {
        return DatabaseInteractionHandler.handleDatabaseInteraction(
            logger,
            { ex -> UserDatabaseException("User database operation failed", ex) },
            interactionCallback
        )
    }

}
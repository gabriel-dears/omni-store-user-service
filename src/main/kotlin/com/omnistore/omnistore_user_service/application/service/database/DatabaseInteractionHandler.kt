package com.omnistore.omnistore_user_service.application.service.database

import org.slf4j.Logger

object DatabaseInteractionHandler {
    fun <T> handleDatabaseInteraction(
        logger: Logger,
        exceptionFactory: (Throwable) -> Exception,
        interactionCallback: () -> T
    ): T {
        return try {
            interactionCallback()
        } catch (ex: Exception) {
            logger.error("Error while interacting with database. Message: ${ex.message}", ex)
            throw exceptionFactory(ex)
        }
    }
}
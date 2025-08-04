package com.omnistore.omnistore_user_service.domain.exception

class UserDatabaseException(message: String, ex: Throwable) : RuntimeException(message, ex) {
}
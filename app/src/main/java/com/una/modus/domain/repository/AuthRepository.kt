package com.una.modus.domain.repository

import com.una.modus.domain.model.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<User>
}
package com.una.modus.domain.usecase

import com.una.modus.domain.model.User
import com.una.modus.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<User> {

        return authRepository.login(
            email = email.trim(),
            password = password
        )
    }
}
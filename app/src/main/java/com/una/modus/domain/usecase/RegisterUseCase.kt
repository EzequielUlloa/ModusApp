package com.una.modus.domain.usecase

import com.una.modus.domain.model.User
import com.una.modus.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        fullName: String,
        email: String,
        password: String
    ): Result<User> {

        return authRepository.register(
            fullName = fullName.trim(),
            email = email.trim(),
            password = password
        )
    }
}
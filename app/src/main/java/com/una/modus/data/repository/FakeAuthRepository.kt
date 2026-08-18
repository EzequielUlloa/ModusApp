package com.una.modus.data.repository

import com.una.modus.domain.model.User
import com.una.modus.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<User> {

        // Simula el tiempo de respuesta de una API.
        delay(1000)

        return if (
            email.equals(
                "estudiante@est.una.ac.cr",
                ignoreCase = true
            ) &&
            password == "123456"
        ) {

            Result.success(
                User(
                    id = 1L,
                    email = "estudiante@est.una.ac.cr",
                    fullName = "Estudiante Modus",
                    role = "ESTUDIANTE"
                )
            )

        } else {

            Result.failure(
                IllegalArgumentException(
                    "Correo o contraseña incorrectos."
                )
            )
        }
    }
}
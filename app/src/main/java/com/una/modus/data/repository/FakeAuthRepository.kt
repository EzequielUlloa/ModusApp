package com.una.modus.data.repository

import com.una.modus.domain.model.User
import com.una.modus.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<User> {

        delay(1000)

        return if (
            email.equals(
                "estudiante@est.una.ac.cr",
                ignoreCase = true
            ) &&
            password == "12345678"
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

    override suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): Result<User> {

        delay(1000)

        /*
         * Correo reservado únicamente para poder probar
         * el estado de error del registro.
         */
        if (
            email.equals(
                "registrado@est.una.ac.cr",
                ignoreCase = true
            )
        ) {
            return Result.failure(
                IllegalArgumentException(
                    "Ya existe una cuenta asociada a este correo."
                )
            )
        }

        return Result.success(
            User(
                id = 2L,
                email = email,
                fullName = fullName,
                role = "ESTUDIANTE"
            )
        )
    }
}
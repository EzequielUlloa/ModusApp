package com.una.modus.domain.model

data class User(
    val id: Long,
    val email: String,
    val fullName: String,
    val role: String
)
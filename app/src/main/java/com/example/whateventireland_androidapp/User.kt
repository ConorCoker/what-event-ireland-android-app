package com.example.whateventireland_androidapp

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    @Transient val password: String,
) : java.io.Serializable
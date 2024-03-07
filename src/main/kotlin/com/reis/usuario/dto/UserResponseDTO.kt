package com.reis.usuario.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigInteger
import java.time.LocalDateTime

data class UserResponseDTO(
    val id: BigInteger?,
    val nick: String?,
    val name: String,
    @JsonProperty("birth_date")
    val birthDate: LocalDateTime,
    val stack: List<StackResponseDTO>?
)

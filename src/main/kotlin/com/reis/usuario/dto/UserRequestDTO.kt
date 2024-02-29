package com.reis.usuario.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class UserRequestDTO(
    val nick: String,
    val name: String,
    @JsonProperty("birth_date")
    val birthDate: LocalDateTime,
    val stack: List<String>
)

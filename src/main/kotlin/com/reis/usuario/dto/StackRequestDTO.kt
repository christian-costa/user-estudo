package com.reis.usuario.dto

import jakarta.validation.constraints.NotBlank
import java.math.BigInteger

data class StackRequestDTO(
    val id: BigInteger?,
    val name: String
)

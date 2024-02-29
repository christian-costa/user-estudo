package com.reis.usuario.controller

import com.reis.usuario.dto.UserRequestDTO
import com.reis.usuario.dto.UserResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger
import java.time.LocalDateTime

@RestController
@RequestMapping("users")
class UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createUser(@RequestBody userDTO: UserRequestDTO) : UserResponseDTO {
        return UserResponseDTO(BigInteger.ONE, "fulano", "Fulano Da Silva", LocalDateTime.now(), listOf("Java", "Kotlin")) ;
    }

}
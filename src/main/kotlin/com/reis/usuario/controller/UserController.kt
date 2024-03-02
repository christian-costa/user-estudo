package com.reis.usuario.controller

import com.reis.usuario.dto.UserRequestDTO
import com.reis.usuario.dto.UserResponseDTO
import com.reis.usuario.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger
import java.time.LocalDateTime

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody userDTO: UserRequestDTO) : UserResponseDTO {
        return userService.createNewUser(userDTO)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateUser(@PathVariable id: BigInteger, @RequestBody userDTO: UserRequestDTO) : UserResponseDTO {
        return UserResponseDTO(BigInteger.ONE, "fulano", "Fulano Da Silva", LocalDateTime.now(), listOf("Java", "Kotlin"))
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findUserId(@PathVariable id: BigInteger) : UserResponseDTO {
        return UserResponseDTO(BigInteger.ONE, "fulano", "Fulano Da Silva", LocalDateTime.now(), listOf("Java", "Kotlin"))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findUsers() : List<UserResponseDTO> {
        return listOf(UserResponseDTO(BigInteger.ONE, "fulano", "Fulano Da Silva", LocalDateTime.now(), listOf("Java", "Kotlin")))
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUserId(@PathVariable id: BigInteger) {

    }

}
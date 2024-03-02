package com.reis.usuario.service

import com.reis.usuario.dto.UserRequestDTO
import com.reis.usuario.dto.UserResponseDTO
import com.reis.usuario.model.Stack
import com.reis.usuario.model.User
import com.reis.usuario.repository.StackRepository
import com.reis.usuario.repository.UserRepository
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class UserService (var userRepository: UserRepository, var stackRepository: StackRepository) {

    fun createNewUser(userRequestDTO: UserRequestDTO) : UserResponseDTO {
        val user = userRepository.save(toNewUserModel(userRequestDTO))
        val stacks = stackRepository.saveAll(userRequestDTO.stack.map { toNewStackModel(user, it)})
        return toDTO(user, stacks)
    }

    fun finUserById(id: BigInteger) : UserResponseDTO {
        return toDTO(userRepository.findById(id).orElseThrow());
    }

    private fun toNewUserModel(userRequestDTO: UserRequestDTO) : User {
        return User(
            null,
            userRequestDTO.nick,
            userRequestDTO.name,
            userRequestDTO.birthDate,
            null
            )
    }

    private fun toNewStackModel(user: User, nameStack: String) : Stack {
        return Stack(null, nameStack, user)
    }

    private fun toDTO(user: User, stacks: List<Stack> ) : UserResponseDTO {
        return UserResponseDTO(
            user.id,
            user.nick,
            user.name,
            user.birthDate,
            stacks.map { it.name }
        )
    }

    private fun toDTO(user: User ) : UserResponseDTO {
        return UserResponseDTO(
            user.id,
            user.nick,
            user.name,
            user.birthDate,
            user.stack?.map { it.name }
        )
    }

}
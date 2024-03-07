package com.reis.usuario.service

import com.reis.usuario.dto.StackRequestDTO
import com.reis.usuario.dto.StackResponseDTO
import com.reis.usuario.model.Stack
import com.reis.usuario.model.User
import com.reis.usuario.repository.StackRepository
import com.reis.usuario.repository.UserRepository
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class StackService (private val stackRepository: StackRepository, private val userRepository: UserRepository) {

    fun addStack(userId: BigInteger, stackDTOs: List<StackRequestDTO>) : List<StackResponseDTO> {
        val user = userRepository.findById(userId).orElseThrow();
        return stackRepository.saveAll(stackDTOs.stream().map { toModel(user, it) }.toList()).map { toDTO(it) }
    }

    fun updateStack(id: BigInteger, stackRequestDTO: StackRequestDTO) :  StackResponseDTO {
        val stack = stackRepository.findById(id).orElseThrow()
        return toDTO(stackRepository.save(Stack(id, stackRequestDTO.name, stack.user)))
    }

    fun deleteStack(stackId: BigInteger) {
        stackRepository.deleteById(stackId)
    }

    private fun toModel(user: User, stackDTO: StackRequestDTO) : Stack {
        return Stack(null, stackDTO.name, user)
    }


    private fun toDTO(stack: Stack) : StackResponseDTO {
        return StackResponseDTO(stack.id, stack.name)
    }
}
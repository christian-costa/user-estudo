package com.reis.usuario.controller

import com.reis.usuario.dto.StackRequestDTO
import com.reis.usuario.dto.StackResponseDTO
import com.reis.usuario.service.StackService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.math.BigInteger

@RestController
@RequestMapping("stacks")
class StackController(private val stackService: StackService) {

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addStack(@PathVariable userId: BigInteger, @RequestBody stackDTOS: List<StackRequestDTO>) : List<StackResponseDTO> {
        return stackService.addStack(userId, stackDTOS)
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateStack(@PathVariable id: BigInteger, @RequestBody stackDTO: StackRequestDTO) : StackResponseDTO {
        return stackService.updateStack(id, stackDTO)
    }

    @DeleteMapping("/{stackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStack(@PathVariable stackId: BigInteger) {
        stackService.deleteStack(stackId);
    }

}
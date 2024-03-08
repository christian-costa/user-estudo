package com.reis.usuario.builder

import com.reis.usuario.dto.StackRequestDTO
import java.math.BigInteger

class StackRequestDTOBuilder private constructor() {

    private var id: BigInteger? = null
    private lateinit var name: String

    companion object {
        fun builder() : StackRequestDTOBuilder {
            val builder = StackRequestDTOBuilder()
            start(builder)
            return builder
        }

        private fun start(builder: StackRequestDTOBuilder) {
            builder
                .name(ConstantsTest.STACK_NAME)
        }
    }

    fun id(id: BigInteger) : StackRequestDTOBuilder {
        this.id = id
        return this
    }

    fun name(name: String) : StackRequestDTOBuilder {
        this.name = name;
        return this
    }

    fun build() : StackRequestDTO {
        return StackRequestDTO(id, name)
    }

}
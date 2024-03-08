package com.reis.usuario.builder

import com.reis.usuario.dto.StackRequestDTO
import com.reis.usuario.dto.UserRequestDTO
import java.time.LocalDateTime

class UserRequestDTOBuilder private constructor() {

    private var nick: String? = null
    private lateinit var name: String
    private lateinit var birthDate: LocalDateTime
    private var stack: List<StackRequestDTO>? = null

    companion object {
        fun builder() : UserRequestDTOBuilder {
            val builder = UserRequestDTOBuilder()
            start(builder)
            return builder
        }

        private fun start(builder: UserRequestDTOBuilder) {
            builder
                .name(ConstantsTest.USER_NAME)
                .nick(ConstantsTest.USER_NICK)
                .birthDate(ConstantsTest.BIRTH_DATE)
        }
    }

    fun nick(nick: String) : UserRequestDTOBuilder {
        this.nick = nick;
        return this
    }

    fun name(name: String) : UserRequestDTOBuilder {
        this.name = name;
        return this
    }

    fun birthDate(birthDate: LocalDateTime) : UserRequestDTOBuilder {
        this.birthDate = birthDate;
        return this
    }

    fun stack(stack: List<StackRequestDTO>) : UserRequestDTOBuilder {
        this.stack = stack
        return this
    }

    fun build() : UserRequestDTO {
        return UserRequestDTO(nick, name, birthDate, stack)
    }
}
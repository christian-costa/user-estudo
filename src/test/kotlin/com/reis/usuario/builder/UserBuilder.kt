package com.reis.usuario.builder

import com.reis.usuario.builder.ConstantsTest.BIRTH_DATE
import com.reis.usuario.builder.ConstantsTest.USER_ID
import com.reis.usuario.builder.ConstantsTest.USER_NAME
import com.reis.usuario.builder.ConstantsTest.USER_NICK
import com.reis.usuario.model.Stack
import com.reis.usuario.model.User
import java.math.BigInteger
import java.time.LocalDateTime

class UserBuilder private constructor(){

    private lateinit var id: BigInteger
    private lateinit var nick: String
    private lateinit var name: String
    private lateinit var birthDate: LocalDateTime
    private var stack: List<Stack> = listOf()

    companion object {
        fun builder() : UserBuilder {
            val builder = UserBuilder()
            start(builder)
            return builder
        }

        private fun start(builder: UserBuilder) {
            builder.id(USER_ID)
                .name(USER_NAME)
                .nick(USER_NICK)
                .birthDate(BIRTH_DATE)
                .stack(listOf())
        }
    }

    fun id(id: BigInteger) : UserBuilder {
        this.id = id;
        return this
    }

    fun nick(nick: String) : UserBuilder {
        this.nick = nick;
        return this
    }

    fun name(name: String) : UserBuilder {
        this.name = name;
        return this
    }

    fun birthDate(birthDate: LocalDateTime) : UserBuilder {
        this.birthDate = birthDate;
        return this
    }

    fun stack(stack: List<Stack>) : UserBuilder {
        this.stack = stack
        return this
    }

    fun build() : User {
        return User(id, nick, name, birthDate, stack)
    }

}
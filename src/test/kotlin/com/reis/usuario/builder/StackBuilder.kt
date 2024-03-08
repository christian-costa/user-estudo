package com.reis.usuario.builder

import com.reis.usuario.model.Stack
import com.reis.usuario.model.User
import java.math.BigInteger

class StackBuilder private constructor() {

    private lateinit var id: BigInteger
    private lateinit var name: String
    private lateinit var user: User

    companion object {
        fun builder() : StackBuilder {
            val builder = StackBuilder()
            start(builder)
            return builder
        }

        private fun start(builder: StackBuilder) {
            builder.id(ConstantsTest.STACK_ID)
                .name(ConstantsTest.STACK_NAME)
                .user(UserBuilder.builder().stack(listOf()).build())
        }
    }

    fun id(id: BigInteger) : StackBuilder {
        this.id = id
        return this
    }

    fun name(name: String) : StackBuilder {
        this.name = name;
        return this
    }

    fun user(user: User) : StackBuilder {
        this.user = user
        return this
    }

    fun build() : Stack {
        return Stack(id, name, user)
    }
}
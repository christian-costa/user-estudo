package com.reis.usuario.service


import com.reis.usuario.builder.ConstantsTest.STACK_ID
import com.reis.usuario.builder.ConstantsTest.STACK_NAME
import com.reis.usuario.builder.ConstantsTest.USER_ID
import com.reis.usuario.builder.ConstantsTest.USER_NAME
import com.reis.usuario.builder.ConstantsTest.USER_NICK
import com.reis.usuario.builder.StackBuilder
import com.reis.usuario.builder.StackRequestDTOBuilder
import com.reis.usuario.builder.UserBuilder
import com.reis.usuario.builder.UserRequestDTOBuilder
import com.reis.usuario.repository.StackRepository
import com.reis.usuario.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {

    private val userRepository: UserRepository = mock()

    private val stackRepository: StackRepository = mock()

    private var userService = UserService(userRepository, stackRepository)

    @Test
    fun deveCriarUsuarioComStacksComsucesso() {
        whenever(userRepository.save(any())).thenReturn(UserBuilder.builder().build())
        whenever(stackRepository.saveAll(anyIterable())).thenReturn(listOf(StackBuilder.builder().build()))

        var result = userService.createNewUser(UserRequestDTOBuilder.builder().stack(listOf(StackRequestDTOBuilder.builder().build())).build())

        Assertions.assertEquals(USER_ID, result.id)
        Assertions.assertEquals(USER_NICK, result.nick)
        Assertions.assertEquals(USER_NAME, result.name)
        Assertions.assertEquals(1, result.stack?.size)
        Assertions.assertEquals(STACK_ID, result.stack?.get(0)?.id)
        Assertions.assertEquals(STACK_NAME, result.stack?.get(0)?.name)

        verify(userRepository).save(any())
        verify(stackRepository).saveAll(anyIterable())
    }

    @Test
    fun deveCriarUsuarioSemStacksComsucesso() {
        whenever(userRepository.save(any())).thenReturn(UserBuilder.builder().build())

        var result = userService.createNewUser(UserRequestDTOBuilder.builder().build())

        Assertions.assertEquals(USER_ID, result.id)
        Assertions.assertEquals(USER_NICK, result.nick)
        Assertions.assertEquals(USER_NAME, result.name)
        Assertions.assertEquals(0, result.stack?.size)

        verify(userRepository).save(any())
        verify(stackRepository, never()).saveAll(anyIterable())
    }
}
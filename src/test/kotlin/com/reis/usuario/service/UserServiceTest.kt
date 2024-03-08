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
import java.util.*

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

    @Test
    fun deveAtualizarUsuarioComSucesso() {
        whenever(userRepository.findById(any())).thenReturn(Optional.of(UserBuilder.builder().build()))
        whenever(userRepository.save(any())).thenReturn(UserBuilder.builder().build())

        var result = userService.updateUser(USER_ID, UserRequestDTOBuilder.builder().build())

        Assertions.assertEquals(USER_ID, result.id)
        Assertions.assertEquals(USER_NICK, result.nick)
        Assertions.assertEquals(USER_NAME, result.name)
        Assertions.assertEquals(0, result.stack?.size)

        verify(userRepository).findById(any())
        verify(userRepository).save(any())
    }

    @Test
    fun buscarUsuarioComSucesso() {
        whenever(userRepository.findById(any())).thenReturn(Optional.of(UserBuilder.builder().build()))

        var result = userService.finUserById(USER_ID)

        Assertions.assertEquals(USER_ID, result.id)
        Assertions.assertEquals(USER_NICK, result.nick)
        Assertions.assertEquals(USER_NAME, result.name)
        Assertions.assertEquals(0, result.stack?.size)

        verify(userRepository).findById(any())
    }

    @Test
    fun buscarUsuariosComSucesso() {
        whenever(userRepository.findAll()).thenReturn(listOf(UserBuilder.builder().build()))

        var results = userService.findAll()

        Assertions.assertEquals(1, results.size)
        Assertions.assertEquals(USER_ID, results.get(0).id)
        Assertions.assertEquals(USER_NICK, results.get(0).nick)
        Assertions.assertEquals(USER_NAME, results.get(0).name)
        Assertions.assertEquals(0, results.get(0).stack?.size)

        verify(userRepository).findAll()
    }

    @Test
    fun deletarUsuario() {

        doNothing().whenever(userRepository).deleteById(any())
        userService.delete(USER_ID)
        verify(userRepository).deleteById(any())

    }


}
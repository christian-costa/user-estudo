package com.reis.usuario.controller

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.reis.usuario.builder.ConstantsTest.BIRTH_DATE
import com.reis.usuario.builder.ConstantsTest.USER_ID
import com.reis.usuario.builder.ConstantsTest.USER_NAME
import com.reis.usuario.builder.ConstantsTest.USER_NICK
import com.reis.usuario.builder.UserRequestDTOBuilder
import com.reis.usuario.dto.UserResponseDTO
import com.reis.usuario.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [UserController::class])
class UserControllerIntegrationTest(@Autowired private val mockMvc: MockMvc) {

    private val responseREST = "{\"id\":1,\"nick\":\"fulano\",\"name\":\"Fulano da Silva\",\"birth_date\":\"2000-01-02T10:10:59\",\"stack\":null}"

    private val listResponseREST = "[$responseREST]"

    @MockBean
    private lateinit var userService: UserService

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        mapper.registerModules(JavaTimeModule())
    }

    @Test
    fun adicionarUsuario() {
        var resquestDTO = UserRequestDTOBuilder.builder().build()
        var responseDTO = UserResponseDTO(USER_ID, USER_NICK, USER_NAME, BIRTH_DATE, null)
        whenever(userService.createNewUser(any())).thenReturn(responseDTO)

        val result = mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(resquestDTO)))
            .andExpect(status().isCreated)
            .andReturn()

        result.response.setDefaultCharacterEncoding(("UTF-8"))

        var response = result.response.contentAsString

        verify(userService).createNewUser(any())

        assertEquals(responseREST, response)

    }

    @Test
    fun alterarUsuario() {
        var resquestDTO = UserRequestDTOBuilder.builder().build()
        var responseDTO = UserResponseDTO(USER_ID, USER_NICK, USER_NAME, BIRTH_DATE, null)
        whenever(userService.updateUser(any(), any())).thenReturn(responseDTO)

        val result = mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", USER_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(resquestDTO)))
            .andExpect(status().isOk)
            .andReturn()

        result.response.setDefaultCharacterEncoding(("UTF-8"))

        var response = result.response.contentAsString

        verify(userService).updateUser(any(), any())

        assertEquals(responseREST, response)

    }

    @Test
    fun buscarUsuario() {
        var responseDTO = UserResponseDTO(USER_ID, USER_NICK, USER_NAME, BIRTH_DATE, null)
        whenever(userService.finUserById(any())).thenReturn(responseDTO)

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", USER_ID))
            .andExpect(status().isOk)
            .andReturn()

        result.response.setDefaultCharacterEncoding(("UTF-8"))

        var response = result.response.contentAsString

        verify(userService).finUserById(any())

        assertEquals(responseREST, response)

    }

    @Test
    fun buscarUsuarios() {
        var responseDTO = UserResponseDTO(USER_ID, USER_NICK, USER_NAME, BIRTH_DATE, null)
        whenever(userService.findAll()).thenReturn(listOf(responseDTO))

        val result = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
            .andExpect(status().isOk)
            .andReturn()

        result.response.setDefaultCharacterEncoding(("UTF-8"))

        var response = result.response.contentAsString

        verify(userService).findAll()

        assertEquals(listResponseREST, response)

    }

    @Test
    fun deletarUsuario() {
        doNothing().whenever(userService).delete(any())

        val result = mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", USER_ID))
            .andExpect(status().isNoContent)
            .andReturn()

        result.response.setDefaultCharacterEncoding(("UTF-8"))

        verify(userService).delete(any())
    }


}
package com.reis.usuario.controller


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.reis.usuario.builder.ConstantsTest.STACK_ID
import com.reis.usuario.builder.ConstantsTest.STACK_NAME
import com.reis.usuario.builder.ConstantsTest.USER_ID
import com.reis.usuario.builder.StackRequestDTOBuilder
import com.reis.usuario.dto.StackResponseDTO
import com.reis.usuario.service.StackService
import org.junit.jupiter.api.Assertions.assertEquals
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
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [StackController::class])
class StackControllerIntegrationTest(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var stackService: StackService

    private val mapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        mapper.registerModules(JavaTimeModule())
    }

    @Test
    fun adicionarNovaStack() {
        var dtos = listOf(StackResponseDTO(STACK_ID, STACK_NAME))
        whenever(stackService.addStack(any(), any())).thenReturn(dtos)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/stacks/{userId}", USER_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(listOf(StackRequestDTOBuilder.builder().build()))))
            .andExpect(status().isCreated)
            .andReturn()

        result.response.setDefaultCharacterEncoding(("UTF-8"))

        var response = result.response.contentAsString

        verify(stackService).addStack(any(), any())

        assertEquals(mapper.writeValueAsString(dtos), response)
    }

    @Test
    fun alterarStack() {
        var dto = StackResponseDTO(STACK_ID, STACK_NAME)
        whenever(stackService.updateStack(any(), any())).thenReturn(dto)

        val result = mockMvc.perform(MockMvcRequestBuilders.put("/stacks/{id}", STACK_ID)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(StackRequestDTOBuilder.builder().build())))
            .andExpect(status().isOk)
            .andReturn()

        result.response.setDefaultCharacterEncoding(("UTF-8"))

        var response = result.response.contentAsString

        verify(stackService).updateStack(any(), any())

        assertEquals(mapper.writeValueAsString(dto), response)

    }

    @Test
    fun deletarStackComSucesso() {

        doNothing().whenever(stackService).deleteStack(any())
        val result : ResultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/stacks/{stackId}", STACK_ID))

        result.andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(stackService).deleteStack(any())
    }

}
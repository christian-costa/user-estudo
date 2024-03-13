package com.reis.usuario.repository

import com.reis.usuario.builder.ConstantsTest.STACK_ID
import com.reis.usuario.builder.ConstantsTest.STACK_NAME
import com.reis.usuario.builder.ConstantsTest.USER_ID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql

@DataJpaTest
class StackRepositoryIntegrationTest {

    @Autowired
    lateinit var stackRepository: StackRepository

    @Test
    @Sql("classpath:data.sql")
    fun buscarStackPorId() {
        var result = stackRepository.findById(STACK_ID).orElseThrow()
        assertEquals(STACK_ID, result.id)
        assertEquals(STACK_NAME, result.name)
        assertEquals(USER_ID, result.user?.id)
    }

}
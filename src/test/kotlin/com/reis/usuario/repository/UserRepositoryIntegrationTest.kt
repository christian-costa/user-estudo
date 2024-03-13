package com.reis.usuario.repository

import com.reis.usuario.builder.ConstantsTest.BIRTH_DATE
import com.reis.usuario.builder.ConstantsTest.USER_ID
import com.reis.usuario.builder.ConstantsTest.USER_NAME
import com.reis.usuario.builder.ConstantsTest.USER_NICK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql

@DataJpaTest
class UserRepositoryIntegrationTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    @Sql("classpath:data.sql")
    fun buscarUsuarioPorId() {
        var result = userRepository.findById(USER_ID).orElseThrow()
        Assertions.assertEquals(USER_ID, result.id)
        Assertions.assertEquals(USER_NAME, result.name)
        Assertions.assertEquals(USER_NICK, result.nick)
        Assertions.assertEquals(BIRTH_DATE, result.birthDate)
        Assertions.assertEquals(1, result.stack?.size)
    }

}
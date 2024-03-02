package com.reis.usuario.repository

import com.reis.usuario.model.Stack
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger
@Repository
interface StackRepository : JpaRepository<Stack, BigInteger>{
}
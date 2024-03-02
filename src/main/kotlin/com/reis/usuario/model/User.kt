package com.reis.usuario.model

import jakarta.persistence.*
import java.math.BigInteger
import java.time.LocalDateTime

@Entity
@Table(name = "tb_user")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: BigInteger?,
    @Column(name = "nick")
    val nick: String,
    @Column(name = "name")
    val name: String,
    @Column(name = "birthdate")
    val birthDate: LocalDateTime,
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY )
    val stack: List<Stack>?
)
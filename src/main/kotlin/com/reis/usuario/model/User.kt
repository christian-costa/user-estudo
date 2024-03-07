package com.reis.usuario.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigInteger
import java.time.LocalDateTime

@Entity
@Table(name = "tb_user")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: BigInteger?,
    @Column(name = "nick", nullable = true, length = 32)
    val nick: String,
    @NotBlank
    @Column(name = "name", length = 255)
    @NotBlank
    val name: String,
    @Column(name = "birthdate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val birthDate: LocalDateTime,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user", fetch = FetchType.LAZY )
    val stack: List<Stack>?
)
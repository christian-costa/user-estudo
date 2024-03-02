package com.reis.usuario.model

import jakarta.persistence.*
import java.math.BigInteger

@Entity
@Table(name = "stack")
data class Stack(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: BigInteger?,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "id_user")
    val user: User?
)

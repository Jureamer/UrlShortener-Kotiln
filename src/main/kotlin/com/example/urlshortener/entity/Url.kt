package com.example.urlshortener.entity

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
@Entity
class Url (

    @Column(nullable = false)
    var longUrl: String,

    @Column(nullable = false)

    var shortUrl: String,

    @Column(nullable = false)
    var used: Boolean = true,

    @Column(nullable = false)
    var createdAt: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var updatedAt: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    var expirationAt: LocalDate = LocalDate.now().plusMonths(1),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    constructor(longUrl: String, shortUrl: String) :
            this(
                longUrl,
                shortUrl,
                true,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                null
            )
    override fun toString(): String = "id: $id, longUrl: $longUrl, shortUrl: $shortUrl, used: $used, createdAt: $createdAt updatedAt: $updatedAt, expirationAt: $expirationAt"
}
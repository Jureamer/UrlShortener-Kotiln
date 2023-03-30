package com.example.urlshortener.entity

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.persistence.*
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
    var used: Boolean = false,

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var expirationAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    constructor(longUrl: String, shortUrl: String) : this(longUrl, shortUrl, false, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), null)
    override fun toString(): String = "id: $id, longUrl: $longUrl, shortUrl: $shortUrl, used: $used, createdAt: $createdAt updatedAt: $updatedAt, expirationAt: $expirationAt"
}

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Int,
//
//    @Column(nullable = false)
//    var used: Boolean = true,
//
//    @Column(nullable = false)
//    var createdAt: LocalDateTime  ,
//
//    @Column(nullable = false)
//    var updatedAt: LocalDateTime  ,
//
//    @Column(nullable = false)
//    var expirationAt: LocalDateTime  ,
//
//    constructor(shortUrl: String, longUrl: String): this(longUrl, shortUrl) {
//        this.shortUrl = shortUrl
//        this.longUrl = longUrl
////        this.used = false
////        this.createdAt = LocalDateTime.now()
////        this.updatedAt = LocalDateTime.now()
////        this.expirationAt = LocalDateTime.now()
//    }
//}



//    @Column()
//    val createdAt: String = new Date().toISOString().slice(0, 19).replace('T', ' '),
//
//    @Column()
//    val updatedAt: String = new Date().toISOString().slice(0, 19).replace('T', ' '),

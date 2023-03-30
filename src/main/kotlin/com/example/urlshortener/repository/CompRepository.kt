package com.example.urlshortener.repository

import com.example.urlshortener.entity.Url
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CompRepository: JpaRepository<Url, Long> {
    abstract fun findByShortUrl(shortUrl: String): Url
    @Query("update Url u set u.expirationAt = :expirationAt where u.id = :id")
    abstract fun updateExpirationAtById(id: Long?, expirationAt: LocalDateTime): Url
    abstract fun findByLongUrl(longUrl: String): Url?
    abstract fun save(generatedShortUrl: String): Url
}
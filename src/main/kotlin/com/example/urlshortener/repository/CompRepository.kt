package com.example.urlshortener.repository

import com.example.urlshortener.entity.Url
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface CompRepository: JpaRepository<Url, Long> {
    abstract fun findByShortUrl(shortUrl: String): Url?
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Url u set u.expirationAt = :expirationAt where u.id = :id")
    abstract fun updateExpirationAtById(@Param("id") id: Long?, @Param("expirationAt") expirationAt: LocalDate): Int
    abstract fun findByLongUrl(longUrl: String): Url?
    abstract fun save(generatedShortUrl: String): Url
}
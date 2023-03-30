package com.example.urlshortener.service

import com.example.urlshortener.entity.Url
import com.example.urlshortener.repository.CompRepository
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter


@Service
class CompService (
    private val compRepository: CompRepository,
    private val env: Environment,
) {
    private val uriLength: Int = 7

    private fun attachServerUrl(url: String): String {
        return env.getProperty("SERVER_HOST") + '/' + url
    }

    fun checkValidation() {
        println("checkValidation")
    }

    private fun getOneMonthAfterBasedCurrentDate(): LocalDateTime = LocalDateTime.now().plusMonths(1)

    fun getRedirectUrl(shortUrl: String): String? {
        try {
            val result: Url? = compRepository.findByShortUrl(shortUrl)
            val oneMonthAfter = getOneMonthAfterBasedCurrentDate()
            // 조회 할 경우 한달 뒤로 업데이트
            result?.let {
                compRepository.updateExpirationAtById(
                    it.id, oneMonthAfter
                )
                return it.longUrl
            } ?: run {
                return null
            }
        } catch (err: Exception) {
            println("Error: $err")
            return null
        }
    }

    private fun convertYYYYMMDD(date: String): LocalDateTime {
        return parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    private fun makeResponseForm(response: Url): Url? {
        //Todo 깊은 복사 필요
        return try {
            response.shortUrl = attachServerUrl(response.shortUrl)
            response.expirationAt = convertYYYYMMDD(response.expirationAt.toString())
            response
        } catch (e: Exception) {
            println("Error $e")
            response.shortUrl = ""
            response.expirationAt = LocalDateTime.now()
            response
        }
    }

    fun shortenUrl(longUrl: String): Url? {


        // 기존에 등록된 URL이면 기존의 shortUrl을 반환
        val result: Url? = compRepository.findByLongUrl(longUrl)

        println("result:::: $result, longUrl:::: $longUrl")
        return result?.let { makeResponseForm(result) } ?: run {
            val generatedShortUrl: String = generateShortUrl(uriLength)
            println("generatedShortUrl $generatedShortUrl")
            val body = Url(longUrl, generatedShortUrl)
            println("body ${body.toString()}")
            compRepository.save(body)
            makeResponseForm(body)
        }

    }

    private fun generateShortUrl(uriLength: Int): String {
        var randomIds = getRandomId(uriLength)
        val isExist: Boolean = compRepository.existsById(1)

        isExist?.let {
            randomIds = getRandomId(uriLength)
        }
        return randomIds
    }

    private fun getRandomId(uriLength: Int): String {
        val base62: String = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var randomId: String = ""

        while (randomId.length < uriLength) {
            val num: Int = (Math.random() * 61).toInt()
            randomId += base62[num]
        }

        return randomId
    }
}

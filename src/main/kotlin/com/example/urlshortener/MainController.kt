package com.example.urlshortener

import com.example.urlshortener.service.CompService
import jakarta.validation.constraints.Size
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping
class MainController(
    private val compService: CompService
) {
    @GetMapping("/{url}")
    fun getRedirectUrl(
        @PathVariable(name="url") shortUrl: String): ResponseEntity<Any> {
        val shortedUrl = compService.getRedirectUrl(shortUrl)
        return if(shortedUrl != null) {
            val redirectUri = URI(shortedUrl)
            val httpHeaders = HttpHeaders()
            httpHeaders.location = redirectUri
            ResponseEntity<Any>(httpHeaders, HttpStatus.PERMANENT_REDIRECT)
        } else  {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL이 존재하지 않습니다.")
        }
    }

//    @ResponseBody
    @GetMapping("")
    fun root(): ResponseEntity<Any> {
        val redirectUri = URI("/main")
        val httpHeaders = HttpHeaders()
        httpHeaders.location = redirectUri
        return ResponseEntity<Any>(httpHeaders, HttpStatus.PERMANENT_REDIRECT)
    }
}
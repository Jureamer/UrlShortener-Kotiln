package com.example.urlshortener.controller

import com.example.urlshortener.service.CompService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.URL
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comp")
class CompController (
    val compService: CompService
){
    @GetMapping
    fun shortenUrl(
        @RequestBody
        @Valid
        @URL
        url: String): Any? {
        val shortUrl = compService.checkValidation(url)

        return shortUrl?.let {
            ResponseEntity.status(200).body("URL이 정상적으로 압축되었습니다")
        } ?: ResponseEntity.status(400).body("URL이 정상적으로 압축되었습니다")
    }
}
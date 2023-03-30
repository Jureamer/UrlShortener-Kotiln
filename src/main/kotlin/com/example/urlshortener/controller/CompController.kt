package com.example.urlshortener.controller

import com.example.urlshortener.controller.model.CompRequestModel
import com.example.urlshortener.entity.Url
import com.example.urlshortener.service.CompService
import jakarta.validation.Valid
import org.hibernate.validator.constraints.URL
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comp")

class CompController (
    val compService: CompService
){
    @PostMapping("")
    fun shortenUrl(
        @RequestBody
        @Valid
        @URL
        compRequestModel: CompRequestModel
    ): Any? {
        val shortUrl: Url? = compService.shortenUrl(compRequestModel.url)
        print("shortUrl $shortUrl")
        return shortUrl?.let {
            ResponseEntity.status(200).body("URL이 정상적으로 압축되었습니다")
        } ?: ResponseEntity.status(400).body("URL이 정상적으로 압축되었습니다")
    }
}
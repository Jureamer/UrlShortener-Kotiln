package com.example.urlshortener.controller

import com.example.urlshortener.controller.model.CompRequestModel
import com.example.urlshortener.controller.model.CompResponseModel
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
        val response: Url? = compService.shortenUrl(compRequestModel.url)
        return response?.let {
            ResponseEntity.status(200).body(CompResponseModel(200, "URL이 정상적으로 압축되었습니다", response))
        } ?: ResponseEntity.status(400).body(CompResponseModel(400, "유효한 URL이 아닙니다.", null))
    }
}
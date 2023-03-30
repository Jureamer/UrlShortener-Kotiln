package com.example.urlshortener.controller.model

import com.example.urlshortener.entity.Url

data class CompResponseModel(
    val status: Int,
    val message: String,
    val data: Url?,
)


package com.example.urlshortener.controller.model

import org.hibernate.validator.constraints.URL
import org.springframework.validation.annotation.Validated

@Validated
data class CompRequestModel (
        @field:URL
        val url: String
    )


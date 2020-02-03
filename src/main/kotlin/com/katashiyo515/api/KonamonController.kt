package com.katashiyo515.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1")
class KonamonController {
    @GetMapping("konamon")
    fun get(): List<String>? {
        return listOf("okonomiyaki","takoyaki")
    }
}
package com.katashiyo515.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class KonamonController {
    @GetMapping("v0.0.1/konamon")
    fun getV1(): List<String> {
        return listOf("okonomiyaki","takoyaki")
    }

    @GetMapping("v0.0.2/konamon")
    @ResponseStatus(HttpStatus.OK)
    fun get(): List<String> {
        System.out.println("get")
        return listOf("okonomiyaki","takoyaki")
    }

    @PostMapping("v0.0.2/konamon")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody(required = true) name:String): String {
        System.out.println("post")
        return name
    }

    @PutMapping("v0.0.2/konamon")
    @ResponseStatus(HttpStatus.OK)
    fun put(@RequestBody(required = true) name:String): String {
        System.out.println("put")
        return name
    }

    @DeleteMapping("v0.0.2/konamon")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestBody(required = true) name:String) {
        System.out.println("delete")
    }
}
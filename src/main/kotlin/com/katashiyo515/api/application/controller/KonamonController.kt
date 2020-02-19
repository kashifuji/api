package com.katashiyo515.api.application.controller

import com.katashiyo515.api.application.mapper.KonamonResourceMapper
import com.katashiyo515.api.application.resource.KonamonResource
import com.katashiyo515.api.domain.repository.KonamonRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors


@RestController
@RequestMapping("api/v0.0.2/konamons")
class KonamonController (var konamonRepository : KonamonRepository) {
    val mapper = Mappers.getMapper(KonamonResourceMapper::class.java)

    @GetMapping(value = ["{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable("id") id : Int): KonamonResource {
        return mapper.konamonToResource(konamonRepository.findById(id))
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getKonamons(): List<KonamonResource> {
        val konamons = konamonRepository.findAll()
        return konamons.stream()
                .map { mapper.konamonToResource(it) }
                .collect(Collectors.toList())
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Validated request : KonamonResource): KonamonResource {
        val konamon = konamonRepository.create(mapper.resourceToKonamon(request))
        return mapper.konamonToResource(konamon)
    }

    @PutMapping(value = ["{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable("id") id : Int, @RequestBody request: KonamonResource): KonamonResource {
        val konamon = konamonRepository.update(mapper.resourceToKonamon(request))
        return mapper.konamonToResource(konamon)
    }

    @DeleteMapping(value = ["{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id : Int) {
        konamonRepository.delete(id)
    }
}
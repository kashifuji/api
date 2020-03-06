package com.katashiyo515.api.application.controller

import com.katashiyo515.api.application.mapper.KonamonResourceMapper
import com.katashiyo515.api.application.resource.KonamonResource
import com.katashiyo515.api.common.error.ApiError

import com.katashiyo515.api.domain.repository.KonamonRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import java.util.stream.Collectors
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["粉もん情報"], description = "粉もん情報を操作する")
@ApiResponses(
    ApiResponse(code = 500, message = "internal server error", response = ApiError::class),
    ApiResponse(code = 400, message = "bad request", response = ApiError::class)
)
@RestController
@RequestMapping("api/v0.0.2/konamons")
class KonamonController(private val konamonRepository: KonamonRepository) {
    val mapper = Mappers.getMapper(KonamonResourceMapper::class.java)

    @ApiOperation(value = "粉もん情報を参照します", notes = "パラメータで指定した粉もん情報を参照します")
    @ApiResponses(
        ApiResponse(code = 404, message = "resource not found", response = ApiError::class)
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["{konamonId}"])
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable("konamonId") id: Int): KonamonResource {
        return mapper.konamonToResource(konamonRepository.findById(id))
    }

    @ApiOperation(value = "粉もん情報リストを参照します", notes = "粉もん情報リストを参照します")
    @ApiResponses(
        ApiResponse(code = 404, message = "resource not found", response = ApiError::class)
    ) @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<KonamonResource> {
        val konamons = konamonRepository.findAll()
        return konamons.stream()
                .map { mapper.konamonToResource(it) }
                .collect(Collectors.toList())
    }

    @ApiOperation(value = "粉もん情報を登録します", notes = "パラメータで指定した粉もん情報を登録します")
    @ApiResponses(
        ApiResponse(code = 409, message = "resource already exists", response = ApiError::class)
    )
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Validated request: KonamonResource): KonamonResource {
        val konamon = konamonRepository.create(mapper.resourceToKonamon(request))
        return mapper.konamonToResource(konamon)
    }

    @ApiOperation(value = "粉もん情報を更新します", notes = "パラメータで指定した粉もん情報を更新します")
    @ApiResponses(
        ApiResponse(code = 409, message = "aborted", response = ApiError::class)
    )
    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["{konamonId}"])
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable("konamonId") id: Int, @RequestBody request: KonamonResource): KonamonResource {
        var reqestkonamon = mapper.resourceToKonamon(request)
        reqestkonamon.id = id
        val konamon = konamonRepository.update(reqestkonamon)
        return mapper.konamonToResource(konamon)
    }

    @ApiResponses(
        ApiResponse(code = 409, message = "aborted", response = ApiError::class)
    )
    @ApiOperation(value = "粉もん情報を削除します", notes = "パラメータで指定した粉もん情報を削除します")
    @DeleteMapping(produces = [MediaType.APPLICATION_JSON_VALUE], value = ["{konamonId}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("konamonId") id: Int) {
        konamonRepository.delete(id)
    }
}



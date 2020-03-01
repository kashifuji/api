package com.katashiyo515.api.application.controller

import com.katashiyo515.api.application.resource.KonamonResource
import com.katashiyo515.api.application.resource.OsakanaResource
import com.katashiyo515.api.common.error.ApiError
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@Api(tags = ["お魚情報"], description = "お魚情報を操作する")
@ApiResponses(
    ApiResponse(code = 500, message = "internal server error", response = ApiError::class),
    ApiResponse(code = 400, message = "bad request", response = ApiError::class)
)
@RestController
@RequestMapping("api/v0.0.1/fishes")
class OsakanaController {
    @ApiOperation(value = "お魚情報リストを参照します", notes = "お魚情報リストを参照します")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun list(): List<OsakanaResource> {
        val osakana = OsakanaResource()
        osakana.osakanaId = 1
        osakana.name = "いなだ"
        osakana.description = "おいしいよ"
        return listOf(
            osakana
        )
    }

}
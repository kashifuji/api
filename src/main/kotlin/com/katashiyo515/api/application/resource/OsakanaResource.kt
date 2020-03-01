package com.katashiyo515.api.application.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size
import org.hibernate.validator.constraints.Range

@ApiModel(description = "お魚", value = "osakana")
class OsakanaResource : Serializable {
    private val serialVersionUID = 1L

    @ApiModelProperty(value = "お魚ID", example = "1", position = 1)
    @Range(min = 0, max = 99999, message = "must be {min} - {max}")
    var osakanaId: Int? = null

    @ApiModelProperty(value = "お魚の名前", example = "イナダ", position = 2)
    @NotEmpty(message = "name can't be empty")
    @Size(min = 0, max = 20, message = "must be {min} - {max}")
    var name: String? = null

    @ApiModelProperty(value = "お魚の説明", example = "ぶりの小さい時の名前。安くておいしい", position = 3)
    @Size(min = 0, max = 100)
    var description: String? = null
}

package com.katashiyo515.api.application.resource

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size
import org.hibernate.validator.constraints.Range

@ApiModel(description = "粉もん", value = "Konamon")
class KonamonResource : Serializable {
    private val serialVersionUID = 1L

    @ApiModelProperty(value = "粉もんID", example = "1", position = 1)
    @Range(min = 0, max = 99999, message = "must be {min} - {max}")
    var konamonId: Int? = null

    @ApiModelProperty(value = "粉もんの名称", example = "お好み焼き", position = 2)
    @NotEmpty(message = "name can't be empty")
    @Size(min = 0, max = 20, message = "must be {min} - {max}")
    var name: String? = null

    @ApiModelProperty(value = "粉もんの説明", example = "小麦粉とキャベツなどを使用する鉄板焼きの一種", position = 3)
    @Size(min = 0, max = 100)
    var description: String? = null

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    var updateTime : Date? = null
}

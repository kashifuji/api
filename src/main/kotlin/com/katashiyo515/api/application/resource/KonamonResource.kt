package com.katashiyo515.api.application.resource

import java.io.Serializable
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size
import org.hibernate.validator.constraints.Range

class KonamonResource : Serializable {
    private val serialVersionUID = 1L

    @Range(min = 0, max = 99999, message = "must be {min} - {max}")
    var konamonId: Int? = null

    @NotEmpty(message = "name can't be empty")
    @Size(min = 0, max = 20, message = "must be {min} - {max}")
    var name: String? = null

    @Size(min = 0, max = 100)
    var description: String? = null

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    var updateTime : Date? = null
}

package com.katashiyo515.api.application.resource

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Range
import java.io.Serializable
import java.util.Date
import javax.validation.constraints.*

class KonamonResource : Serializable {
    private val serialVersionUID = 1L

    @Range(min = 0, max = 99999, message = "must be {min} - {max}")
    var konamonId : Int? = null

    @NotEmpty(message = "name can't be empty")
    @Size(min = 0, max = 20, message = "must be {min} - {max}")
    var name : String? = null

    @Size(min = 0, max = 100)
    var description : String? = null

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    var updateTime : Date? = null
}
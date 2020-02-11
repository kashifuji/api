package com.katashiyo515.api.application.resource

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

class KonamonResource : Serializable {
    private val serialVersionUID = 1L

    @NotEmpty
    @Size(max = 20)
    var konamonId : Int = 0

    @NotEmpty
    @Size(max = 20)
    var name : String? = null

    @Size(max = 100)
    var description : String? = null
}
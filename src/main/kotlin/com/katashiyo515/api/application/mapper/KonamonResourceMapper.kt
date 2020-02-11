package com.katashiyo515.api.application.mapper

import com.katashiyo515.api.application.resource.KonamonResource
import com.katashiyo515.api.domain.entity.Konamon
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface KonamonResourceMapper {
    @Mapping(source = "id", target = "konamonId")
    fun konamonToResource(konamon : Konamon) : KonamonResource

    @Mapping(source = "konamonId", target = "id")
    fun resourceToKonamon(konamonResource : KonamonResource) : Konamon
}
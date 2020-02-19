package com.katashiyo515.api.infrastructure

import com.katashiyo515.api.common.error.ErrorCodeDefinition
import com.katashiyo515.api.common.exception.KonamonResourceNotFoundException
import com.katashiyo515.api.common.message.Message
import com.katashiyo515.api.domain.entity.Konamon
import com.katashiyo515.api.domain.repository.KonamonRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class KonamonRepositoryImpl : KonamonRepository {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    override fun findById(id : Int): Konamon {
        log.info("findById id = {}", id)
        if (id == 0) {
            log.error("[{}] {}",ErrorCodeDefinition.KonamonResourceNotFound.code, Message.getMessage(ErrorCodeDefinition.KonamonResourceNotFound.code, id))
            throw KonamonResourceNotFoundException()
        }
        return Konamon(
                id = id,
                name = "okonomiyaki",
                description = "mettya umai")
    }

    override fun findAll(): List<Konamon> {
        log.info("findAll")
        return listOf(
                Konamon(id = 1, name = "okonomiyaki", description = "umai"),
                Konamon(id = 2, name = "takoyaki", description = "umaiiyo")
        )
    }

    override fun create(konamon: Konamon): Konamon {
        log.info("create")
        return konamon
    }

    override fun update(konamon: Konamon): Konamon {
        log.info("update")
        return konamon
    }

    override fun delete(id: Int) {
        log.info("delete")
    }
}
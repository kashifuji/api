package com.katashiyo515.api.infrastructure

import com.katashiyo515.api.domain.entity.Konamon
import com.katashiyo515.api.domain.repository.KonamonRepository
import org.springframework.stereotype.Repository

@Repository
class KonamonRepositoryImpl : KonamonRepository {
    override fun findById(id : Int): Konamon {
        System.out.println("findById")
        return Konamon(
                id = id,
                name = "okonomiyaki",
                description = "mettya umai")
    }

    override fun findAll(): List<Konamon> {
        System.out.println("findAll")
        return listOf(
                Konamon(id = 1, name = "okonomiyaki", description = "umai"),
                Konamon(id = 2, name = "takoyaki", description = "umaiiyo")
        )
    }

    override fun create(konamon: Konamon): Konamon {
        System.out.println("create")
        return konamon
    }

    override fun update(konamon: Konamon): Konamon {
        System.out.println("update")
        return konamon
    }

    override fun delete(id: Int) {
        System.out.println("delete")
    }
}
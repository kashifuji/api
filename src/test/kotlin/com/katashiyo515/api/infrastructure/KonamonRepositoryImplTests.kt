package com.katashiyo515.api.infrastructure

import com.katashiyo515.api.domain.entity.Konamon
import com.katashiyo515.api.infrastructure.repository.KonamonRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class KonamonRepositoryImplTests {

    @Autowired
    private lateinit var target: KonamonRepositoryImpl

    @Test
    fun findByIdTest () {
        var actual = target.findById(1)

        var expected = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")

        assertEquals(1, actual.id)
        assertEquals(expected, actual)
    }
}
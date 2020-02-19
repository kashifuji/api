package com.katashiyo515.api.infrastructure

import com.katashiyo515.api.domain.entity.Konamon
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KonamonRepositoryImplTests {
    @Test
    fun findByIdTest () {
        var target = KonamonRepositoryImpl()
        var actual = target.findById(1)

        var expected = Konamon(id = 1, name = "okonomiyaki", description = "mettya umai")

        assertEquals(1, actual.id)
        assertEquals(expected, actual)
    }
}
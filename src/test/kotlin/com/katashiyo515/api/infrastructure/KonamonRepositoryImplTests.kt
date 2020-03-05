package com.katashiyo515.api.infrastructure

import com.katashiyo515.api.common.exception.ResourceDelicateException
import com.katashiyo515.api.common.exception.ResourceLockedException
import com.katashiyo515.api.common.exception.ResourceNotFoundException
import com.katashiyo515.api.domain.entity.Konamon
import com.katashiyo515.api.infrastructure.mapper.KonamonDbMapper
import com.katashiyo515.api.infrastructure.repository.KonamonRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.dao.CannotAcquireLockException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class KonamonRepositoryImplTests {

    @Autowired
    private lateinit var target: KonamonRepositoryImpl

    @MockBean
    private lateinit var konamonDbMapper: KonamonDbMapper

    @Test
    fun findByIdTest() {
        val input = 1
        val expected = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
        Mockito.`when`(konamonDbMapper.findById(input)).thenReturn(expected)

        val actual = target.findById(input)

        assertEquals(expected, actual)
        verify(konamonDbMapper, times(1)).findById(input)
    }

    @Test()
    fun findByIdExceptionTest() {
        val input = 1
        Mockito.`when`(konamonDbMapper.findById(input)).thenReturn(null)

        assertThrows(ResourceNotFoundException::class.java) {
            target.findById(input)
        }
        verify(konamonDbMapper, times(1)).findById(input)
    }

    @Test
    fun findAllTest() {
        val expected = listOf(Konamon(id = 1, name = "okonomiyaki", description = "umaiyo"))
        Mockito.`when`(konamonDbMapper.findAll()).thenReturn(expected)

        val actual = target.findAll()

        assertEquals(expected, actual)
        verify(konamonDbMapper, times(1)).findAll()
    }

    @Test()
    fun findAllExceptionTest() {
        Mockito.`when`(konamonDbMapper.findAll()).thenReturn(listOf())

        assertThrows(ResourceNotFoundException::class.java) {
            target.findAll()
        }
        verify(konamonDbMapper, times(1)).findAll()
    }

    @Test
    fun createTest() {
        val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
        val expected = input
        Mockito.doNothing().`when`(konamonDbMapper).insert(input)

        val actual = target.create(input)

        assertEquals(expected, actual)
        verify(konamonDbMapper, times(1)).insert(expected)
    }

    @Test()
    fun createExceptionTest() {
        val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
        val expectedId = input.id
        val expectedCause = DataIntegrityViolationException("hoge")
        Mockito.`when`(konamonDbMapper.insert(input)).thenThrow(expectedCause)

        val actual = assertThrows(ResourceDelicateException::class.java) {
            target.create(input)
        }

        assertEquals(expectedId, actual.id)
        assertEquals(expectedCause, actual.cause)
        verify(konamonDbMapper, times(1)).insert(input)
    }

    @Test
    fun updateTest() {
        val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
        val expected = input

        Mockito.`when`(konamonDbMapper.update(input)).thenReturn(1)

        val actual = target.update(input)

        assertEquals(expected, actual)
        verify(konamonDbMapper, times(1)).update(input)
    }

    @Test()
    fun updateExceptionTest() {
        val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
        val expectedId = input.id
        val expectedCause = CannotAcquireLockException("hoge")
        Mockito.`when`(konamonDbMapper.update(input)).thenThrow(expectedCause)

        val actual = assertThrows(ResourceLockedException::class.java) {
            target.update(input)
        }

        assertEquals(expectedId, actual.id)
        assertEquals(expectedCause, actual.cause)
        verify(konamonDbMapper, times(1)).update(input)
    }

    @Test()
    fun updateExceptionTest2() {
        val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
        Mockito.`when`(konamonDbMapper.update(input)).thenReturn(0)

        assertThrows(ResourceNotFoundException::class.java) {
            target.update(input)
        }
        verify(konamonDbMapper, times(1)).update(input)
    }

    @Test
    fun deleteTest() {
        val input = 1
        Mockito.`when`(konamonDbMapper.delete(input)).thenReturn(1)

        target.delete(input)

        verify(konamonDbMapper, times(1)).delete(input)
    }

    @Test()
    fun deleteExceptionTest() {
        val input = 1
        val expectedId = input
        val expectedCause = CannotAcquireLockException("hoge")
        Mockito.`when`(konamonDbMapper.delete(input)).thenThrow(expectedCause)

        val actual = assertThrows(ResourceLockedException::class.java) {
            target.delete(input)
        }

        assertEquals(expectedId, actual.id)
        assertEquals(expectedCause, actual.cause)
        verify(konamonDbMapper, times(1)).delete(input)
    }

    @Test()
    fun deleteExceptionTest2() {
        val input = 1
        Mockito.`when`(konamonDbMapper.delete(input)).thenReturn(0)

        assertThrows(ResourceNotFoundException::class.java) {
            target.delete(input)
        }
        verify(konamonDbMapper, times(1)).delete(input)
    }
}

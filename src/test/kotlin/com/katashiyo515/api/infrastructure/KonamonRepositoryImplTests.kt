package com.katashiyo515.api.infrastructure

import com.katashiyo515.api.common.exception.ResourceDelicateException
import com.katashiyo515.api.common.exception.ResourceLockedException
import com.katashiyo515.api.common.exception.ResourceNotFoundException
import com.katashiyo515.api.domain.entity.Konamon
import com.katashiyo515.api.infrastructure.mapper.KonamonDbMapper
import com.katashiyo515.api.infrastructure.repository.KonamonRepositoryImpl
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.reset
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

    @BeforeEach
    fun setup() {
        reset(konamonDbMapper)
    }

    @Nested
    inner class FindByIdTests {
        @Test
        @DisplayName("検索条件に該当するデータがある場合粉もんリソースを返す")
        fun normal() {
            val input = 1
            val expected = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
            Mockito.`when`(konamonDbMapper.findById(input)).thenReturn(expected)

            val actual = target.findById(input)

            assertEquals(expected, actual)
            assertThat(actual, `is`(expected)) // Matchers
            assertThat(actual).isEqualTo(expected) // assertj
            verify(konamonDbMapper, times(1)).findById(input)
        }

        @Test()
        @DisplayName("検索条件に該当するデータがない場合例外が発生する")
        fun abnormal() {
            val input = 1
            Mockito.`when`(konamonDbMapper.findById(input)).thenReturn(null)

            assertThrows(ResourceNotFoundException::class.java) {
                target.findById(input)
            }
            verify(konamonDbMapper, times(1)).findById(input)
        }
    }

    @Nested
    inner class findAllTests {
        @Test
        @DisplayName("データがある場合粉もんリソースを返す")
        fun normal() {
            val expected = listOf(Konamon(id = 1, name = "okonomiyaki", description = "umaiyo"))
            Mockito.`when`(konamonDbMapper.findAll()).thenReturn(expected)

            val actual = target.findAll()

            assertEquals(expected, actual)
            verify(konamonDbMapper, times(1)).findAll()
        }

        @Test()
        @DisplayName("データがない場合例外が発生する")
        fun abnormal() {
            Mockito.`when`(konamonDbMapper.findAll()).thenReturn(listOf())

            assertThrows(ResourceNotFoundException::class.java) {
                target.findAll()
            }
            verify(konamonDbMapper, times(1)).findAll()
        }
    }

    @Nested
    inner class createTests {
        @Test
        @DisplayName("粉もんリソースがDBに登録できた場合登録した粉もんリソースを返す")
        fun normal() {
            val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
            val expected = input
            Mockito.doNothing().`when`(konamonDbMapper).insert(input)

            val actual = target.create(input)

            assertEquals(expected, actual)
            verify(konamonDbMapper, times(1)).insert(expected)
        }

        @Test()
        @DisplayName("粉もんリソースがDBに登録できない場合例外が発生する")
        fun abnormal() {
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
    }

    @Nested
    inner class updateTests {
        @Test
        @DisplayName("更新できた場合更新した粉もんリソースを返す")
        fun normal() {
            val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
            val expected = input

            Mockito.`when`(konamonDbMapper.update(input)).thenReturn(1)

            val actual = target.update(input)

            assertEquals(expected, actual)
            verify(konamonDbMapper, times(1)).update(input)
        }

        @Test()
        @DisplayName("更新対象がロックされている場合例外が発生する")
        fun abnormal() {
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
        @DisplayName("更新対象がない場合例外が発生する")
        fun abnormal2() {
            val input = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
            Mockito.`when`(konamonDbMapper.update(input)).thenReturn(0)

            assertThrows(ResourceNotFoundException::class.java) {
                target.update(input)
            }
            verify(konamonDbMapper, times(1)).update(input)
        }
    }

    @Nested
    inner class deleteTests {
        @Test
        @DisplayName("削除できた場合何も返さない")
        fun normal() {
            val input = 1
            Mockito.`when`(konamonDbMapper.delete(input)).thenReturn(1)

            target.delete(input)

            verify(konamonDbMapper, times(1)).delete(input)
        }

        @Test()
        @DisplayName("削除対象がロックされている場合例外が発生する")
        fun abnormal() {
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
        @DisplayName("削除対象がない場合例外が発生する")
        fun abnormal2() {
            val input = 1
            Mockito.`when`(konamonDbMapper.delete(input)).thenReturn(0)

            assertThrows(ResourceNotFoundException::class.java) {
                target.delete(input)
            }
            verify(konamonDbMapper, times(1)).delete(input)
        }
    }
}

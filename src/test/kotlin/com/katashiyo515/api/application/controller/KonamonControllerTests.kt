package com.katashiyo515.api.application.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.katashiyo515.api.domain.entity.Konamon
import com.katashiyo515.api.domain.repository.KonamonRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class KonamonControllerTests {
    @Mock
    lateinit var konamonRepository: KonamonRepository

    @InjectMocks
    lateinit var target: KonamonController

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(target).build()
    }

    @Nested
    inner class GetTests() {
        @Test
        @DisplayName("getで/api/v0.0.2/konamons/{konamonId}にリクエストした場合粉もんリソースを1件取得する")
        fun normal() {
            val input = 1
            val expected = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
            Mockito.doReturn(expected).`when`(konamonRepository).findById(input)

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v0.0.2/konamons/{konamonId}", input))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(jsonPath("$.konamonId").value(expected.id!!))
                .andExpect(jsonPath("$.name").value(expected.name!!))
                .andExpect(jsonPath("$.description").value(expected.description!!))

            Mockito.verify(konamonRepository, Mockito.times(1)).findById(input)
        }
    }

    @Nested
    inner class ListTests() {
        @Test
        @DisplayName("getで/api/v0.0.2/konamonsにリクエストした場合粉もんリソースを全件取得する")
        fun normal() {
            val expected = listOf(
                Konamon(id = 1, name = "okonomiyaki", description = "umaiyo"),
                Konamon(id = 2, name = "hoge", description = "hogehoge")
            )

            Mockito.doReturn(expected).`when`(konamonRepository).findAll()

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v0.0.2/konamons"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(jsonPath("$[0].konamonId").value(expected[0].id!!))
                .andExpect(jsonPath("$[0].name").value(expected[0].name!!))
                .andExpect(jsonPath("$[0].description").value(expected[0].description!!))
                .andExpect(jsonPath("$[1].konamonId").value(expected[1].id!!))
                .andExpect(jsonPath("$[1].name").value(expected[1].name!!))
                .andExpect(jsonPath("$[1].description").value(expected[1].description!!))

            Mockito.verify(konamonRepository, Mockito.times(1)).findAll()
        }
    }

    @Nested
    inner class CreateTests() {
        @Test
        @DisplayName("postで/api/v0.0.2/konamonsにリクエストした場合1件の粉もんリソースが登録する")
        fun normal() {
            val input = Konamon(id = null, name = "okonomiyaki", description = "umaiyo")
            val expected = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")

            Mockito.doReturn(expected).`when`(konamonRepository).create(input)

            val mapper = ObjectMapper()
            val request = mapper.writeValueAsString(input)

            mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v0.0.2/konamons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
            )
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(jsonPath("$.konamonId").value(expected.id.toString()))
                .andExpect(jsonPath("$.name").value(expected.name.toString()))
                .andExpect(jsonPath("$.description").value(expected.description.toString()))

            Mockito.verify(konamonRepository, Mockito.times(1)).create(input)
        }
    }

    @Nested
    inner class UpdateTests() {
        @Test
        @DisplayName("putで/api/v0.0.2/konamons/{konamonId}にリクエストした場合1件の粉もんリソースを更新する")
        fun normal() {
            val inputId = 1
            val inputKonamon = Konamon(id = null, name = "okonomiyaki", description = "umaiyo")
            val mockInput = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")
            val expected = Konamon(id = 1, name = "okonomiyaki", description = "umaiyo")

            Mockito.doReturn(expected).`when`(konamonRepository).update(mockInput)

            val mapper = ObjectMapper()
            val request = mapper.writeValueAsString(inputKonamon)

            mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v0.0.2/konamons/{konamonId}", inputId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
            )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(jsonPath("$.konamonId").value(expected.id.toString()))
                .andExpect(jsonPath("$.name").value(expected.name.toString()))
                .andExpect(jsonPath("$.description").value(expected.description.toString()))

            Mockito.verify(konamonRepository, Mockito.times(1)).update(mockInput)
        }
    }

    @Nested
    inner class DeleteTests() {
        @Test
        @DisplayName("deleteで/api/v0.0.2/konamons/{konamonId}にリクエストした場合1件の粉もんリソースを削除する")
        fun normal() {
            val input = 1

            Mockito.doNothing().`when`(konamonRepository).delete(input)

            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v0.0.2/konamons/{konamonId}", input))
                .andExpect(MockMvcResultMatchers.status().isNoContent)

            Mockito.verify(konamonRepository, Mockito.times(1)).delete(input)
        }
    }
}

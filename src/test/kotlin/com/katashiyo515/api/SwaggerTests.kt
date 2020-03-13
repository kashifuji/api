package com.katashiyo515.api

import java.io.File
import java.io.FileOutputStream
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@SpringBootTest
class SwaggerTests {
    @Autowired
    lateinit var context: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    @Throws(Exception::class)
    fun createSwaggerJson() {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs")
            .accept(MediaType.APPLICATION_JSON))
            .andDo { result: MvcResult ->
                val directory = "docs/swagger"
                val outputDir = File(directory)
                if (!outputDir.exists()) {
                    outputDir.mkdirs()
                }
                FileOutputStream("$directory/swagger.json").use { fos ->
                    fos.write(result.response.contentAsByteArray)
                    fos.flush()
                }
            }
            .andExpect(MockMvcResultMatchers.status().isOk())
    }
}

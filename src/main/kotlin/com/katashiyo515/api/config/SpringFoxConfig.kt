package com.katashiyo515.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SpringFoxConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api/.*"))
            .build()
            .apiInfo(apiInfo("食べ物API", "食べ物情報を操作する", ""))
    }

    @Bean
    fun konamonApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .groupName("konaon")
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api/v0.0.2/konamons.*"))
            .build()
            .apiInfo(apiInfo("粉もんAPI", "粉もん情報を操作する", "0.0.2"))
    }

    @Bean
    fun osakanaApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .groupName("fish")
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api/v0.0.1/fishes.*"))
            .build()
            .apiInfo(apiInfo("お魚API", "お魚情報を操作する", "0.0.1"))
    }

    private fun apiInfo(title: String, description: String, version: String): ApiInfo? {
        return ApiInfoBuilder()
            .title(title)
            .description(description)
            .version(version) // (3)
            .license("Apache License v2.0") // (4)
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0") // (5)
            .build()
    }
}

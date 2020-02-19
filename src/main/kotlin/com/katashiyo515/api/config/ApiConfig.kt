//package com.katashiyo515.api.config
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.MessageSource
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.validation.Validator
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
//
//
//@Configuration
//class ApiConfig {
//    @Autowired
//    private var messageSource: MessageSource? = null
//    @Bean
//    fun validator(): LocalValidatorFactoryBean? {
//        val localValidatorFactoryBean = LocalValidatorFactoryBean()
//        messageSource?.let { localValidatorFactoryBean.setValidationMessageSource(it) }
//        return localValidatorFactoryBean
//    }
//    override fun getValidator(): Validator? {
//        return validator()
//    }
//    @Bean
//    fun resourceBundleMessageSource(): MessageSource? {
//        val messageSource = ResourceBundleMessageSource()
//        messageSource.addBasenames("message")
////        messageSource.setCacheSeconds(10)
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource
//    }
//    @Bean
//    fun reloadableResourceBundleMessageSource(): MessageSource? {
//        val messageSource = ReloadableResourceBundleMessageSource()
//        messageSource.addBasenames("classpath:message")
//        return messageSource
//    }
//
//}
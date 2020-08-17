package com.demo.dynamopoc.delivery.configuration

import com.demo.dynamopoc.core.book.BookFactory
import com.demo.dynamopoc.infrastructure.BookFactoryAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookFactoryDefinition {

    @Bean
    fun bookFactory(): BookFactory {
        return BookFactoryAdapter()
    }
}
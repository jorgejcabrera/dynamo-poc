package com.demo.dynamopoc.delivery.configuration

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasures
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RetrieveDBMeasuresDefinition {

    @Bean
    fun retrieveDBMeasures(
            noSqlBookRepository: BookRepository,
            sqlBookRepository: BookRepository
    ): RetrieveDBMeasures {
        return RetrieveDBMeasures(noSqlBookRepository, sqlBookRepository)
    }
}
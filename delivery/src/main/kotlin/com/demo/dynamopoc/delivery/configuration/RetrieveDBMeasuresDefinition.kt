package com.demo.dynamopoc.delivery.configuration

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.measure.DBMeasureService
import com.demo.dynamopoc.core.measure.DBMeasureServiceAdapter
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasures
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasuresUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RetrieveDBMeasuresDefinition {

    @Bean
    fun dbMeasureService(
            noSqlBookRepository: BookRepository,
            sqlBookRepository: BookRepository
    ): DBMeasureService {
        return DBMeasureServiceAdapter(noSqlBookRepository, sqlBookRepository)
    }

    @Bean
    fun retrieveDBMeasuresUseCase(dbMeasureService: DBMeasureService): RetrieveDBMeasuresUseCase {
        return RetrieveDBMeasures(dbMeasureService)
    }
}
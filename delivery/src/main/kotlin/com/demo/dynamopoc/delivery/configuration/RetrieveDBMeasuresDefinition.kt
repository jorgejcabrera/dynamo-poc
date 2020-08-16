package com.demo.dynamopoc.delivery.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasures
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBookRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RetrieveDBMeasuresDefinition {

    @Bean
    fun noSqlBookRepository(dynamoDBMapper: DynamoDBMapper): BookRepository {
        return DynamoBookRepository(dynamoDBMapper)
    }

    @Bean
    fun retrieveDBMeasures(noSqlBookRepository: BookRepository): RetrieveDBMeasures {
        return RetrieveDBMeasures(noSqlBookRepository)
    }
}
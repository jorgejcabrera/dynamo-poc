package com.demo.dynamopoc.delivery.configuration

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasures
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBookRepository
import com.demo.dynamopoc.infrastructure.mysql.JpaBookRepository
import com.demo.dynamopoc.infrastructure.mysql.MySqlBookRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RetrieveDBMeasuresDefinition {

    @Bean
    fun sqlBookRepository(jpaBookRepository: JpaBookRepository): BookRepository {
        return MySqlBookRepository(jpaBookRepository)
    }

    @Bean
    fun noSqlBookRepository(dynamoDBMapper: DynamoDBMapper): BookRepository {
        return DynamoBookRepository(dynamoDBMapper)
    }

    @Bean
    fun retrieveDBMeasures(
            noSqlBookRepository: BookRepository,
            sqlBookRepository: BookRepository
    ): RetrieveDBMeasures {
        return RetrieveDBMeasures(noSqlBookRepository, sqlBookRepository)
    }
}
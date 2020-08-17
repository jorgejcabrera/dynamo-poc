package com.demo.dynamopoc.delivery.configuration.datasource

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.demo.dynamopoc.core.book.BookFactory
import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.delivery.configuration.datasource.dynamo.DynamoDataSourceConfiguration
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBookRepository
import com.demo.dynamopoc.infrastructure.mysql.JpaBookRepository
import com.demo.dynamopoc.infrastructure.mysql.MySqlBookRepository
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataSourceDefinition {

    @Bean
    fun sqlBookRepository(jpaBookRepository: JpaBookRepository): BookRepository {
        return MySqlBookRepository(jpaBookRepository)
    }

    @Bean
    fun noSqlBookRepository(dynamoDBMapper: DynamoDBMapper): BookRepository {
        return DynamoBookRepository(dynamoDBMapper)
    }

    @Bean(initMethod = "execute")
    fun dataSourceInitializer(
            dynamoDataSourceConfiguration: DynamoDataSourceConfiguration,
            bookFactory: BookFactory,
            noSqlBookRepository: BookRepository,
            sqlBookRepository: BookRepository
    ): DataSourceInitializer {
        return DataSourceInitializer(
                dynamoDataSourceConfiguration,
                bookFactory,
                noSqlBookRepository,
                sqlBookRepository
        )
    }
}
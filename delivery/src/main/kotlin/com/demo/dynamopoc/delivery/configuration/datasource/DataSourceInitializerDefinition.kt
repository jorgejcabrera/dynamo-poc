package com.demo.dynamopoc.delivery.configuration.datasource

import com.demo.dynamopoc.core.book.BookFactory
import com.demo.dynamopoc.delivery.configuration.datasource.dynamo.DynamoDataSourceConfiguration
import com.demo.dynamopoc.infrastructure.mysql.JpaBookRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataSourceInitializerDefinition {

    @Bean(initMethod = "execute")
    fun dataSourceInitializer(
            dynamoDataSourceConfiguration: DynamoDataSourceConfiguration,
            bookFactory: BookFactory,
            jpaBookRepository: JpaBookRepository
    ): DataSourceInitializer {
        return DataSourceInitializer(
                dynamoDataSourceConfiguration,
                bookFactory,
                jpaBookRepository
        )
    }
}
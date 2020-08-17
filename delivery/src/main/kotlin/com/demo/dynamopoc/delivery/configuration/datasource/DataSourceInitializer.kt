package com.demo.dynamopoc.delivery.configuration.datasource

import com.demo.dynamopoc.core.book.BookFactory
import com.demo.dynamopoc.delivery.configuration.datasource.dynamo.DynamoDataSourceConfiguration
import com.demo.dynamopoc.infrastructure.BookDto
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBook
import com.demo.dynamopoc.infrastructure.mysql.JpaBookRepository
import com.demo.dynamopoc.infrastructure.mysql.MySqlBook
import org.slf4j.LoggerFactory


class DataSourceInitializer(
        private val dynamoDataSourceConfiguration: DynamoDataSourceConfiguration,
        private val bookFactory: BookFactory,
        private val jpaBookRepository: JpaBookRepository
) {

    fun execute() {
        dynamoDataSourceConfiguration.createSchema()
        createAndSaveSomeBooks()
    }

    private fun createAndSaveSomeBooks() {
        repeat(1000) {
            val book = bookFactory.randomBook() as BookDto
            dynamoDataSourceConfiguration.addBook(book.toDynamoBook())
            jpaBookRepository.save(book.toMySqlBook())
            LOGGER.warn("Creating a new book...")
        }
    }

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val LOGGER = LoggerFactory.getLogger(javaClass.enclosingClass)
    }
}
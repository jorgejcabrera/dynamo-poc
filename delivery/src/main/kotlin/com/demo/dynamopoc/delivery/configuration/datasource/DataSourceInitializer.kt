package com.demo.dynamopoc.delivery.configuration.datasource

import com.demo.dynamopoc.core.book.BookFactory
import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.delivery.configuration.datasource.dynamo.DynamoDataSourceConfiguration
import com.demo.dynamopoc.infrastructure.BookDto
import org.slf4j.LoggerFactory

class DataSourceInitializer(
        private val dynamoDataSourceConfiguration: DynamoDataSourceConfiguration,
        private val bookFactory: BookFactory,
        private val noSqlBookRepository: BookRepository,
        private val sqlBookRepository: BookRepository
) {

    fun execute() {
        dynamoDataSourceConfiguration.createSchema()
        createAndSaveSomeBooks()
    }

    private fun createAndSaveSomeBooks() {
        repeat(10) {
            val book = bookFactory.randomBook() as BookDto
            noSqlBookRepository.save(book.toDynamoBook())
            sqlBookRepository.save(book.toMySqlBook())
            LOGGER.warn("Creating a new book...")
        }
    }

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val LOGGER = LoggerFactory.getLogger(javaClass.enclosingClass)
    }
}
package com.demo.dynamopoc.delivery.configuration.datasource.mysql

import com.demo.dynamopoc.infrastructure.mysql.JpaBookRepository
import com.demo.dynamopoc.infrastructure.mysql.MySqlBook
import com.demo.dynamopoc.infrastructure.mysql.MySqlBookFactory
import org.slf4j.LoggerFactory

class MySqlSchemaInitializer(private val jpaBookRepository: JpaBookRepository) {

    fun initialize() {
        val factory = MySqlBookFactory()
        repeat(10000) {
            val book = factory.randomBook() as MySqlBook
            jpaBookRepository.save(book)
            LOGGER.debug("Creating a new mysql book...")
        }
    }

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val LOGGER = LoggerFactory.getLogger(javaClass.enclosingClass)
    }
}
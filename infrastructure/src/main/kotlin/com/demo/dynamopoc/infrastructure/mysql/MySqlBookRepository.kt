package com.demo.dynamopoc.infrastructure.mysql

import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.BookRepository

class MySqlBookRepository(private val jpaBookRepository: JpaBookRepository) : BookRepository {
    override fun findAll(): List<Book> {
        return jpaBookRepository.findAll()
    }

    override fun findAllByCreatedDateBetween(): List<Book> {
        TODO("Not yet implemented")
    }
}
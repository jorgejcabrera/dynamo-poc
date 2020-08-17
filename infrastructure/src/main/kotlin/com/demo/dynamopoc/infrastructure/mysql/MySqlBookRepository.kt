package com.demo.dynamopoc.infrastructure.mysql

import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.book.Category
import java.util.*

class MySqlBookRepository(private val jpaBookRepository: JpaBookRepository) : BookRepository {
    override fun findAll(): List<Book> {
        return jpaBookRepository.findAll()
    }

    override fun save(book: Book) {
        jpaBookRepository.save(book as MySqlBook)
    }

    override fun findAllByCreatedDateBefore(date: Date): List<Book> {
        return jpaBookRepository.findAllByCreatedDateBefore(date)
    }

    override fun findAllByPriceGreaterThan(price: Double): List<Book> {
        return jpaBookRepository.findAllByPriceGreaterThan(price)
    }

    override fun findAllByCategoryAndPriceGreaterThan(category: Category, price: Double): List<Book> {
        return jpaBookRepository.findAllByCategoryAndPriceGreaterThan(category, price)
    }
}
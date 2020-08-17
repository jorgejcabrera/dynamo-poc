package com.demo.dynamopoc.core.book

import java.util.*

interface BookRepository {
    fun findAll(): List<Book>
    fun save(book: Book)
    fun findAllByCreatedDateBefore(date: Date): List<Book>
    fun findAllByPriceGreaterThan(price: Double): List<Book>
}
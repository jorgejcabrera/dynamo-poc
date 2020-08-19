package com.demo.dynamopoc.core.book

import java.util.*

interface BookRepository {
    fun findAll(): List<Book>
    fun findAllByCategoryAndPriceGreaterThan(category: Category, price: Double): List<Book>
    fun findAllByCreatedDateBefore(date: Date): List<Book>
    fun save(book: Book)
    fun findAllByCategory(category: Category): List<Book>
    fun findAllByPriceGreaterThan(price: Double): List<Book>
}
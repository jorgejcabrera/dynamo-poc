package com.demo.dynamopoc.core.book

import java.util.*

interface BookRepository {
    fun findAll(): List<Book>
    fun save(book: Book)
    fun findAllByCreatedDateBeforeAt(date: Date): List<Book>
}
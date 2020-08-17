package com.demo.dynamopoc.core.book

interface BookRepository {
    fun findAll(): List<Book>
    fun save(book: Book)
    fun findAllByCreatedDateBetween(): List<Book>
}
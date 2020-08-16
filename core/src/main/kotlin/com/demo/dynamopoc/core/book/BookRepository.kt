package com.demo.dynamopoc.core.book

interface BookRepository {
    fun findAll(): List<Book>
    fun findAllByCreatedDateBetween(): List<Book>
}
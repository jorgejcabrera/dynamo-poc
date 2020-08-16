package com.demo.dynamopoc.infrastructure.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.BookRepository

class DynamoBookRepository(private val mapper: DynamoDBMapper) : BookRepository {
    override fun findAll(): List<Book> {
        val query = DynamoDBQueryExpression<DynamoBook>()
        return mapper.query(DynamoBook::class.java, query)
    }

    override fun findAllByCreatedDateBetween(): List<Book> {
        TODO("Not yet implemented")
    }
}
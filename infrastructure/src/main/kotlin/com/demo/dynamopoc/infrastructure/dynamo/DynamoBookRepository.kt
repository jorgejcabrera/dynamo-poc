package com.demo.dynamopoc.infrastructure.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.BookRepository

class DynamoBookRepository(private val mapper: DynamoDBMapper) : BookRepository {
    override fun findAll(): List<Book> {
        val query = DynamoDBScanExpression()
        return mapper.scan(DynamoBook::class.java, query)
    }

    override fun findAllByCreatedDateBetween(): List<Book> {
        TODO("Not yet implemented")
    }
}
package com.demo.dynamopoc.infrastructure.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.book.Category
import java.text.SimpleDateFormat
import java.util.*

class DynamoBookRepository(private val mapper: DynamoDBMapper) : BookRepository {

    private var dateFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    init {
        dateFormatter.timeZone = TimeZone.getTimeZone("UTC")
    }

    override fun findAll(): List<Book> {
        val query = DynamoDBScanExpression()
        return mapper.scan(DynamoBook::class.java, query)
    }

    override fun save(book: Book) {
        mapper.save(book)
    }

    override fun findAllByCreatedDateBefore(date: Date): List<Book> {
        val mapped = AttributeValue().withS(dateFormatter.format(date))
        val args = Collections.singletonMap(":v1", mapped)
        val query = DynamoDBScanExpression()
                .withIndexName("created_date_idx")
                .withFilterExpression("created_date < :v1")
                .withExpressionAttributeValues(args)
        return mapper.scan(DynamoBook::class.java, query)
    }

    override fun findAllByPriceGreaterThan(price: Double): List<Book> {
        val mapped = AttributeValue().withN(price.toString())
        val args = Collections.singletonMap(":v1", mapped)
        val query = DynamoDBScanExpression()
                .withIndexName("price_idx")
                .withFilterExpression("price > :v1")
                .withExpressionAttributeValues(args)
        return mapper.scan(DynamoBook::class.java, query)
    }

    override fun findAllByCategoryAndPriceGreaterThan(category: Category, price: Double): List<Book> {
        val args = mutableMapOf<String, AttributeValue>()
        args[":v1"] = AttributeValue().withN(price.toString())
        args[":v2"] = AttributeValue().withS(price.toString())
        val query = DynamoDBScanExpression()
                .withIndexName("price_idx")
                .withFilterExpression("price > :v1 and category = :v2")
                .withExpressionAttributeValues(args)
        return mapper.scan(DynamoBook::class.java, query)
    }
}
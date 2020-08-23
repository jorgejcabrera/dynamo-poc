package com.demo.dynamopoc.infrastructure.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
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

    override fun findAllByCategory(category: Category): List<Book> {
        val eav = mutableMapOf<String, AttributeValue>()
        eav[":val1"] = AttributeValue().withS(category.toString())
        val q = DynamoDBQueryExpression<DynamoBook>()
                .withKeyConditionExpression("category = :val1")
                .withExpressionAttributeValues(eav)
        return mapper.query(DynamoBook::class.java, q)
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

    override fun findAllByRatingGreaterThan(rating: Int): List<Book> {
        val mapped = AttributeValue().withN(rating.toString())
        val args = Collections.singletonMap(":v1", mapped)
        val query = DynamoDBScanExpression()
                .withFilterExpression("rating > :v1")
                .withExpressionAttributeValues(args)
        return mapper.scan(DynamoBook::class.java, query)
    }

    override fun findAllByCategoryAndCreatedDateAfter(category: Category, date: Date): List<Book> {
        val eav = mutableMapOf<String, AttributeValue>()
        eav[":v1"] = AttributeValue().withS(dateFormatter.format(date))
        eav[":v2"] = AttributeValue().withS(category.toString())
        val query = DynamoDBScanExpression()
                .withFilterExpression("created_date > :v1 and category = :v2")
                .withExpressionAttributeValues(eav)
        return mapper.scan(DynamoBook::class.java, query)
    }

    override fun findAllByCategoryAndPriceGreaterThan(category: Category, price: Double): List<Book> {
        val eav = mutableMapOf<String, AttributeValue>()
        eav[":v1"] = AttributeValue().withN(price.toString())
        eav[":v2"] = AttributeValue().withS(category.toString())
        val query = DynamoDBScanExpression()
                .withFilterExpression("price > :v1 and category = :v2")
                .withExpressionAttributeValues(eav)
        return mapper.scan(DynamoBook::class.java, query)
    }
}
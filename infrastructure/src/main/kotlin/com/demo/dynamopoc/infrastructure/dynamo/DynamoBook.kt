package com.demo.dynamopoc.infrastructure.dynamo

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.Category
import java.util.*

@DynamoDBTable(tableName = "Book")
class DynamoBook : Book {
    // Partition key
    @get:DynamoDBHashKey(attributeName = "category")
    @DynamoDBTypeConvertedEnum
    override var category: Category?

    // Local secondary index
    @get:DynamoDBAttribute(attributeName = "title")
    @DynamoDBRangeKey
    override var title: String

    // Sorting key
    @get:DynamoDBAttribute(attributeName = "created_date")
    @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
    @DynamoDBIndexRangeKey(localSecondaryIndexName = "created_date_idx")
    override var createdDate: Date?

    @get:DynamoDBAttribute(attributeName = "price")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "price_idx")
    override var price: Double?

    @get:DynamoDBAttribute(attributeName = "rating")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "rating_idx")
    override var rating: Int?

    constructor() {
        this.title = ""
        this.price = null
        this.rating = null
        this.category = null
        this.createdDate = null
    }

    constructor(
            title: String,
            category: Category?,
            createdDate: Date?,
            price: Double?,
            rating: Int?
    ) {
        this.title = title
        this.price = price
        this.rating = rating
        this.category = category
        this.createdDate = createdDate
    }
}
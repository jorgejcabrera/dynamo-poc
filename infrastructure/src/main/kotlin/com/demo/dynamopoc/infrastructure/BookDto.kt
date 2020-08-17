package com.demo.dynamopoc.infrastructure

import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.Category
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBook
import com.demo.dynamopoc.infrastructure.mysql.MySqlBook
import java.util.*

class BookDto(
        override var title: String,
        override var category: Category?,
        override var createdDate: Date?,
        override var price: Double,
        override var rating: Int
) : Book {

    fun toDynamoBook(): DynamoBook {
        return DynamoBook(
                category = this.category,
                title = this.title,
                price = this.price,
                rating = this.rating,
                createdDate = this.createdDate
        )
    }

    fun toMySqlBook(): MySqlBook {
        return MySqlBook(
                category = this.category,
                title = this.title,
                price = this.price,
                rating = this.rating,
                createdDate = this.createdDate
        )
    }
}
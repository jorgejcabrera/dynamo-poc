package com.demo.dynamopoc.infrastructure

import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.BookFactory
import com.demo.dynamopoc.core.book.Category
import java.util.*
import kotlin.random.Random

class BookFactoryAdapter : BookFactory {
    override fun randomBook(): Book {
        val category = Category.values().random()
        val title = UUID.randomUUID().toString()
        val price = Random.nextDouble()
        val rating = Random.nextInt(0, 5)
        return BookDto(
                category = category,
                title = title,
                price = price,
                rating = rating,
                createdDate = Date()
        )
    }
}
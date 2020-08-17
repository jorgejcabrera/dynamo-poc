package com.demo.dynamopoc.infrastructure.mysql

import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.Category
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "book")
@IdClass(MySqlBookId::class)
class MySqlBook : Book {
    @Id
    @Column(name = "title")
    override var title: String = ""

    @Id
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    override var category: Category? = null

    @Column(name = "created_date", updatable = false)
    override var createdDate: Date? = null

    @Column(name = "price")
    override var price: Double? = null

    @Column(name = "rating")
    override var rating: Int? = null

    constructor()
    constructor(
            title: String,
            category: Category?,
            createdDate: Date?,
            price: Double?,
            rating: Int?
    ) {
        this.title = title
        this.category = category
        this.createdDate = createdDate
        this.price = price
        this.rating = rating
    }
}
package com.demo.dynamopoc.infrastructure.mysql

import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.Group
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
    override var group: Group? = null

    @Column(name = "created_date", updatable = false)
    override var createdDate: Date? = null

    @Column(name = "price")
    override var price: Double = 0.0

    @Column(name = "rating")
    override var rating: Int = 0

    constructor()
    constructor(title: String, group: Group?, createdDate: Date?, price: Double, rating: Int) {
        this.title = title
        this.group = group
        this.createdDate = createdDate
        this.price = price
        this.rating = rating
    }
}
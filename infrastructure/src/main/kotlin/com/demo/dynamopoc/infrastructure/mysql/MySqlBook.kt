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
    override var title: String = ""
    @Id
    override var group: Group? = null
    override var createdDate: Date? = null
    override var price: Double = 0.0
    override var rating: Int = 0


    constructor()
}
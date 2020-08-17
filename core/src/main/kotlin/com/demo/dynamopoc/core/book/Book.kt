package com.demo.dynamopoc.core.book

import java.util.*

interface Book {
    var title: String
    var category: Category?
    var createdDate: Date?
    var price: Double
    var rating: Int
}
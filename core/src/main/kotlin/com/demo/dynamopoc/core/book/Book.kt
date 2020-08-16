package com.demo.dynamopoc.core.book

import java.util.*

interface Book {
    var title: String
    var group: String
    var createdDate: Date?
    var price: Double
    var rating: Int
}
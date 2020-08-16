package com.demo.dynamopoc.core.book

import java.util.*

interface Book {
    val title: String?
    val group: String?
    val createdDate: Date?
    val price: Double?
    val rating: Int?
}
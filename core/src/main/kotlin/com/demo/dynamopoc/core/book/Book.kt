package com.demo.dynamopoc.core.book

import java.time.LocalDate

interface Book {
    val title: String
    val group: String
    val createdDate: LocalDate
    val price: Double
    val rating: Int
}
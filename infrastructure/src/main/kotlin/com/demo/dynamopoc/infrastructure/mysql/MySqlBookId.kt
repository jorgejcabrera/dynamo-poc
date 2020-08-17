package com.demo.dynamopoc.infrastructure.mysql

import com.demo.dynamopoc.core.book.Category
import java.io.Serializable

class MySqlBookId : Serializable {
    var title: String = ""
    var group: Category? = null
}
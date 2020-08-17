package com.demo.dynamopoc.infrastructure.mysql

import com.demo.dynamopoc.core.book.Group
import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class MySqlBookId : Serializable {
    var title: String = ""
    var group: Group? = null
}
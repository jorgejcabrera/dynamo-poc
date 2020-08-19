package com.demo.dynamopoc.infrastructure.mysql

import com.demo.dynamopoc.core.book.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JpaBookRepository : CrudRepository<MySqlBook, MySqlBookId> {
    override fun findAll(): List<MySqlBook>
    fun findAllByCreatedDateBefore(date: Date): List<MySqlBook>
    fun findAllByCategory(category: Category): List<MySqlBook>
    fun findAllByPriceGreaterThan(price: Double): List<MySqlBook>
    fun findAllByCategoryAndPriceGreaterThan(category: Category, price: Double): List<MySqlBook>
}
package com.demo.dynamopoc.infrastructure.mysql

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JpaBookRepository : CrudRepository<MySqlBook, MySqlBookId> {
    override fun findAll(): List<MySqlBook>
    fun findAllByCreatedDateBefore(date: Date): List<MySqlBook>
    fun findAllByPriceGreaterThan(price: Double): List<MySqlBook>
}
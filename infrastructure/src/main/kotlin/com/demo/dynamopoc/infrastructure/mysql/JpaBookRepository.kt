package com.demo.dynamopoc.infrastructure.mysql

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaBookRepository : CrudRepository<MySqlBook, MySqlBookId> {
    override fun findAll(): List<MySqlBook>
}
package com.demo.dynamopoc.infrastructure.mysql

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MySqlBookRepository : CrudRepository<MySqlBook, MySqlBookId> {

}
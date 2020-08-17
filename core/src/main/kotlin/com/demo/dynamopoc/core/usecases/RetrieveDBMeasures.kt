package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.report.Report
import kotlin.system.measureTimeMillis

class RetrieveDBMeasures(
        private val noSqlBookRepository: BookRepository,
        private val sqlBookRepository: BookRepository
) {

    fun execute(): Report {
        val noSqlScanQueryTime = measureTimeMillis { noSqlBookRepository.findAll() }
        val sqlScanQueryTime = measureTimeMillis { sqlBookRepository.findAll() }
        return Report(
                sqlScanQueryTime = sqlScanQueryTime,
                noSqlScanQueryTime = noSqlScanQueryTime
        )
    }
}
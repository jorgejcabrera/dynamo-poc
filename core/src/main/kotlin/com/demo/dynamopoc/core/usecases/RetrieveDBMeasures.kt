package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.report.Report
import kotlin.system.measureTimeMillis

class RetrieveDBMeasures(
        private val sqlBookRepository: BookRepository,
        private val noSqlBookRepository: BookRepository
) {

    fun execute(): Report {
        val sqlScanQueryTime = measureTimeMillis { sqlBookRepository.findAll() }
        val noSqlScanQueryTime = measureTimeMillis { noSqlBookRepository.findAll() }
        return Report(sqlScanQueryTime = sqlScanQueryTime, noSqlScanQueryTime = noSqlScanQueryTime)
    }
}
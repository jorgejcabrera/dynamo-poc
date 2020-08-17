package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.report.Report
import kotlin.system.measureTimeMillis

class RetrieveDBMeasures(
        private val noSqlBookRepository: BookRepository,
        private val sqlBookRepository: BookRepository
) {

    fun execute(): Report {
        var report = Report(mutableMapOf(), mutableMapOf())
        val noSqlScanQueryTime = measureTimeMillis { noSqlBookRepository.findAll() }
        val sqlScanQueryTime = measureTimeMillis { sqlBookRepository.findAll() }
        report.dynamoMeasures["scan_query_time"] = noSqlScanQueryTime
        report.mySqlMeasures["scan_query_time"] = sqlScanQueryTime
        return report
    }
}
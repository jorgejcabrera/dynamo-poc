package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.report.Report
import org.joda.time.LocalDateTime
import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class RetrieveDBMeasures(
        private val noSqlBookRepository: BookRepository,
        private val sqlBookRepository: BookRepository
) {

    fun execute(): Report {
        val report = Report(mutableMapOf(), mutableMapOf())
        val noSqlScanQueryTime = measureTimeMillis { noSqlBookRepository.findAll() }
        val sqlScanQueryTime = measureTimeMillis { sqlBookRepository.findAll() }
        report.dynamoMeasures["scan_query_time"] = noSqlScanQueryTime
        report.mySqlMeasures["scan_query_time"] = sqlScanQueryTime

        val noSqlQueryTimeByDate = measureTimeMillis { noSqlBookRepository.findAllByCreatedDateBefore(Date()) }
        val sqlQueryTimeByDate = measureTimeMillis { sqlBookRepository.findAllByCreatedDateBefore(Date()) }
        report.dynamoMeasures["query_time_by_date"] = noSqlQueryTimeByDate
        report.mySqlMeasures["query_time_by_date"] = sqlQueryTimeByDate

        val noSqlQueryTimeByPrice = measureTimeMillis { noSqlBookRepository.findAllByPriceGreaterThan(10.0) }
        val sqlQueryTimeByPrice = measureTimeMillis { sqlBookRepository.findAllByPriceGreaterThan(10.0) }
        report.dynamoMeasures["query_time_by_price"] = noSqlQueryTimeByPrice
        report.mySqlMeasures["query_time_by_price"] = sqlQueryTimeByPrice
        return report
    }

    fun randomDate(): Date {
        return LocalDateTime().minusDays(Random.nextInt(0, 600)).toDate()
    }
}
package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.book.Category
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

        val date = randomDate()
        val noSqlQueryTimeByDate = measureTimeMillis { noSqlBookRepository.findAllByCreatedDateBefore(date) }
        val sqlQueryTimeByDate = measureTimeMillis { sqlBookRepository.findAllByCreatedDateBefore(date) }
        report.dynamoMeasures["query_time_by_date"] = noSqlQueryTimeByDate
        report.mySqlMeasures["query_time_by_date"] = sqlQueryTimeByDate

        val price = randomPrice()
        val noSqlQueryTimeByPrice = measureTimeMillis { noSqlBookRepository.findAllByPriceGreaterThan(price) }
        val sqlQueryTimeByPrice = measureTimeMillis { sqlBookRepository.findAllByPriceGreaterThan(price) }
        report.dynamoMeasures["query_time_by_price"] = noSqlQueryTimeByPrice
        report.mySqlMeasures["query_time_by_price"] = sqlQueryTimeByPrice

        val category = randomCategory()
        val noSqlQueryTimeByCategoryAndPrice = measureTimeMillis {
            noSqlBookRepository.findAllByCategoryAndPriceGreaterThan(category, price)
        }
        val sqlQueryTimeByCategoryAndPrice = measureTimeMillis {
            noSqlBookRepository.findAllByCategoryAndPriceGreaterThan(category, price)
        }
        report.dynamoMeasures["query_time_by_category_and_price"] = noSqlQueryTimeByCategoryAndPrice
        report.mySqlMeasures["query_time_by_category_and_price"] = sqlQueryTimeByCategoryAndPrice
        return report
    }

    private fun randomPrice(): Double {
        return Random.nextDouble()
    }

    private fun randomCategory(): Category {
        return Category.values().random()
    }

    fun randomDate(): Date {
        return LocalDateTime().minusDays(Random.nextInt(0, 600)).toDate()
    }
}
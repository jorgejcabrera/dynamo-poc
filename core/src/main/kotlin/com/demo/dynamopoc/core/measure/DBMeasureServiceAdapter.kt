package com.demo.dynamopoc.core.measure

import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.book.Category
import org.joda.time.LocalDateTime
import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class DBMeasureServiceAdapter(
        private val noSqlBookRepository: BookRepository,
        private val sqlBookRepository: BookRepository
) : DBMeasureService {

    override fun queryScan(): DBMeasure {
        val noSqlQueryTime = measureTimeMillis { noSqlBookRepository.findAll() }
        val sqlQueryTime = measureTimeMillis { sqlBookRepository.findAll() }
        return DBMeasure(
                description = "scan_query_time",
                noSqlQueryTime = noSqlQueryTime,
                sqlQueryTime = sqlQueryTime
        )
    }

    override fun queryByDate(): DBMeasure {
        val date = randomDate()
        val noSqlQueryTime = measureTimeMillis { noSqlBookRepository.findAllByCreatedDateBefore(date) }
        val sqlQueryTime = measureTimeMillis { sqlBookRepository.findAllByCreatedDateBefore(date) }
        return DBMeasure(
                description = "query_time_by_date",
                noSqlQueryTime = noSqlQueryTime,
                sqlQueryTime = sqlQueryTime
        )
    }

    override fun queryByPrice(): DBMeasure {
        val price = randomPrice()
        val noSqlQueryTime = measureTimeMillis { noSqlBookRepository.findAllByPriceGreaterThan(price) }
        val sqlQueryTime = measureTimeMillis { sqlBookRepository.findAllByPriceGreaterThan(price) }
        return DBMeasure(
                description = "query_time_by_price",
                noSqlQueryTime = noSqlQueryTime,
                sqlQueryTime = sqlQueryTime
        )
    }

    override fun queryByCategoryAndPrice(): DBMeasure {
        val price = randomPrice()
        val category = randomCategory()
        val noSqlQueryTime = measureTimeMillis {
            noSqlBookRepository.findAllByCategoryAndPriceGreaterThan(category, price)
        }
        val sqlQueryTime = measureTimeMillis {
            sqlBookRepository.findAllByCategoryAndPriceGreaterThan(category, price)
        }
        return DBMeasure(
                description = "query_time_by_category_and_price",
                noSqlQueryTime = noSqlQueryTime,
                sqlQueryTime = sqlQueryTime
        )
    }

    override fun queryByCategory(): DBMeasure {
        val category = randomCategory()
        val noSqlQueryTime = measureTimeMillis {
            noSqlBookRepository.findAllByCategory(category)
        }
        val sqlQueryTime = measureTimeMillis {
            sqlBookRepository.findAllByCategory(category)
        }
        return DBMeasure(
                description = "query_time_by_category",
                noSqlQueryTime = noSqlQueryTime,
                sqlQueryTime = sqlQueryTime
        )
    }

    private fun randomPrice(): Double {
        return Random.nextDouble()
    }

    private fun randomCategory(): Category {
        return Category.values().random()
    }

    private fun randomDate(): Date {
        return LocalDateTime().minusDays(Random.nextInt(0, 600)).toDate()
    }
}
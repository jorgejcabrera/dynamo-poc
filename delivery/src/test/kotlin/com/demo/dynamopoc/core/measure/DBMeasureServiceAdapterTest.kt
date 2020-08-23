package com.demo.dynamopoc.core.measure

import com.demo.dynamopoc.core.book.Book
import com.demo.dynamopoc.core.book.BookRepository
import com.demo.dynamopoc.core.book.Category.ADVENTURE
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class DBMeasureServiceAdapterTest {

    @Mock
    private lateinit var noSqlBookRepository: BookRepository

    @Mock
    private lateinit var sqlBookRepository: BookRepository

    private lateinit var dbMeasuresService: DBMeasureService

    private val books = listOf<Book>()
    private val date = Date()
    private val price = 20.0
    private val category = ADVENTURE
    private val rating = 1

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dbMeasuresService = DBMeasureServiceAdapter(noSqlBookRepository, sqlBookRepository)
        
        whenever(noSqlBookRepository.findAllByCategoryAndCreatedDateAfter(category, date)).thenReturn(books)
        whenever(sqlBookRepository.findAllByCategoryAndCreatedDateAfter(category, date)).thenReturn(books)
        whenever(noSqlBookRepository.findAllByRatingGreaterThan(rating)).thenReturn(books)
        whenever(sqlBookRepository.findAllByRatingGreaterThan(rating)).thenReturn(books)
        whenever(noSqlBookRepository.findAllByPriceGreaterThan(price)).thenReturn(books)
        whenever(sqlBookRepository.findAllByPriceGreaterThan(price)).thenReturn(books)
        whenever(noSqlBookRepository.findAll()).thenReturn(books)
        whenever(sqlBookRepository.findAll()).thenReturn(books)
        whenever(noSqlBookRepository.findAllByCreatedDateBefore(date)).thenReturn(books)
        whenever(sqlBookRepository.findAllByCreatedDateBefore(date)).thenReturn(books)
        whenever(noSqlBookRepository.findAllByCategory(category)).thenReturn(books)
        whenever(sqlBookRepository.findAllByCategory(category)).thenReturn(books)
        whenever(noSqlBookRepository.findAllByCategoryAndPriceGreaterThan(category, price)).thenReturn(books)
        whenever(sqlBookRepository.findAllByCategoryAndPriceGreaterThan(category, price)).thenReturn(books)
    }

    @Test
    fun `when execute a query scan then should return a db measure`() {
        // when
        val dbMeasure = dbMeasuresService.queryScan()

        // then
        thenScanQueriesWasExecuted()
        thenADbMeasureWasReturned(dbMeasure)
        thenDbMeasureDescriptionWasNotEmpty(dbMeasure)
    }

    @Test
    fun `when execute a query by date then should return a db measure`() {
        // when
        val dbMeasure = dbMeasuresService.queryByDate()

        // then
        thenQueryByDateWasExecuted()
        thenDbMeasureDescriptionWasNotEmpty(dbMeasure)
    }

    @Test
    fun `when execute a query by price then should return a db measure`() {
        // when
        val dbMeasure = dbMeasuresService.queryByPrice()

        // then
        thenQueryByPriceWasExecuted()
        thenDbMeasureDescriptionWasNotEmpty(dbMeasure)
    }

    @Test
    fun `when execute a query by price and category then should return a db measure`() {
        // when
        val dbMeasure = dbMeasuresService.queryByCategoryAndPrice()

        // then
        thenQueryByPriceAndCategoryWasExecuted()
        thenDbMeasureDescriptionWasNotEmpty(dbMeasure)
    }

    @Test
    fun `when execute a query by category then should return a db measure`() {
        // when
        val dbMeasure = dbMeasuresService.queryByCategory()

        // then
        thenQueryByCategoryWasExecuted()
        thenDbMeasureDescriptionWasNotEmpty(dbMeasure)
    }

    @Test
    fun `when execute a query by rating then should return a db measure`() {
        // when
        val dbMeasure = dbMeasuresService.queryByRating()

        // then
        thenQueryByRatingWasExecuted()
        thenDbMeasureDescriptionWasNotEmpty(dbMeasure)
    }

    @Test
    fun `when execute a query by category and created date then should return a db measure`() {
        // when
        val dbMeasure = dbMeasuresService.queryByCategoryAndCreatedDate()

        // then
        thenQueryByCategoryAndCreatedDateWasExecuted()
        thenDbMeasureDescriptionWasNotEmpty(dbMeasure)
    }

    private fun thenQueryByCategoryAndCreatedDateWasExecuted() {
        verify(noSqlBookRepository).findAllByCategoryAndCreatedDateAfter(any(), any())
        verify(sqlBookRepository).findAllByCategoryAndCreatedDateAfter(any(), any())
    }

    private fun thenQueryByRatingWasExecuted() {
        verify(noSqlBookRepository).findAllByRatingGreaterThan(any())
        verify(sqlBookRepository).findAllByRatingGreaterThan(any())
    }

    private fun thenQueryByCategoryWasExecuted() {
        verify(noSqlBookRepository).findAllByCategory(any())
        verify(sqlBookRepository).findAllByCategory(any())
    }

    private fun thenQueryByPriceAndCategoryWasExecuted() {
        verify(noSqlBookRepository).findAllByCategoryAndPriceGreaterThan(any(), any())
        verify(sqlBookRepository).findAllByCategoryAndPriceGreaterThan(any(), any())
    }

    private fun thenQueryByPriceWasExecuted() {
        verify(noSqlBookRepository, times(1)).findAllByPriceGreaterThan(any())
        verify(sqlBookRepository, times(1)).findAllByPriceGreaterThan(any())
    }

    private fun thenQueryByDateWasExecuted() {
        verify(noSqlBookRepository, times(1)).findAllByCreatedDateBefore(any())
        verify(sqlBookRepository, times(1)).findAllByCreatedDateBefore(any())
    }

    private fun thenScanQueriesWasExecuted() {
        verify(noSqlBookRepository, times(1)).findAll()
        verify(sqlBookRepository, times(1)).findAll()
    }

    private fun thenDbMeasureDescriptionWasNotEmpty(dbMeasure: DBMeasure) {
        assertNotNull(dbMeasure.description)
    }

    private fun thenADbMeasureWasReturned(dbMeasure: DBMeasure) {
        assertNotNull(dbMeasure)
    }

}
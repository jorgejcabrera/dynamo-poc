package com.demo.dynamopoc.delivery.rest

import com.demo.dynamopoc.core.report.Report
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasuresUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val dbMeasuresPath = "/database/measures"

@RestController
@RequestMapping(dbMeasuresPath)
class RetrieveDBMeasuresController(private val retrieveDBMeasures: RetrieveDBMeasuresUseCase) {

    @GetMapping(path = ["/scan_query"])
    fun retrieveScanMeasure(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.executeScanQuery()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }

    @GetMapping(path = ["/query_by_category"])
    fun retrieveQueryByCategoryMeasure(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.executeQueryByCategory()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }

    @GetMapping(path = ["/query_by_category_and_created_date"])
    fun retrieveQueryByCategoryAndCreatedDateMeasure(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.executeQueryByCategoryAndCreatedDate()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }

    @GetMapping(path = ["/query_by_category_and_price"])
    fun retrieveQueryByCategoryAndPriceMeasure(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.executeQueryByCategoryAndPrice()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }

    @GetMapping(path = ["/query_by_price"])
    fun retrieveQueryByPriceMeasure(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.executeQueryByPrice()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }

    @GetMapping(path = ["/query_by_date"])
    fun retrieveQueryByDateMeasure(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.executeQueryByDate()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }

    @GetMapping(path = ["/query_by_rating"])
    fun retrieveQueryByRatingMeasure(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.executeQueryByRating()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }
}
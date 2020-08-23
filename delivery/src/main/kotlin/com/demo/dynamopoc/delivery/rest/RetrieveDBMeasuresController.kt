package com.demo.dynamopoc.delivery.rest

import com.demo.dynamopoc.core.report.Report
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasures
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

    @GetMapping
    fun retrieveDBMeasures(): ResponseEntity<Report> {
        val report = retrieveDBMeasures.execute()
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(report)
    }
}
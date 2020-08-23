package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.measure.DBMeasureService
import com.demo.dynamopoc.core.report.Report

class RetrieveDBMeasures(private val dbMeasureService: DBMeasureService) {

    fun execute(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryByCategoryAndCreatedDate())
                .addMeasure(dbMeasureService.queryScan())
                .addMeasure(dbMeasureService.queryByDate())
                .addMeasure(dbMeasureService.queryByPrice())
                .addMeasure(dbMeasureService.queryByCategoryAndPrice())
                .addMeasure(dbMeasureService.queryByCategory())
                .addMeasure(dbMeasureService.queryByRating())
                .build()
    }
}
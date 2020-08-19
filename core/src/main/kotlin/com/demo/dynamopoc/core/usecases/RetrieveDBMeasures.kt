package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.measure.DBMeasureService
import com.demo.dynamopoc.core.report.Report

class RetrieveDBMeasures(private val dbMeasureService: DBMeasureService) {

    fun execute(): Report {
        val report = Report(mutableListOf())
        report.measures.add(dbMeasureService.queryScan())
        report.measures.add(dbMeasureService.queryByDate())
        report.measures.add(dbMeasureService.queryByPrice())
        report.measures.add(dbMeasureService.queryByCategoryAndPrice())
        report.measures.add(dbMeasureService.queryByCategory())
        return report
    }
}
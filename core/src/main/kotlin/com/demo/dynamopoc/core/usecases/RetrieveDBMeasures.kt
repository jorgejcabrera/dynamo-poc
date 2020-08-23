package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.measure.DBMeasureService
import com.demo.dynamopoc.core.report.Report

class RetrieveDBMeasures(private val dbMeasureService: DBMeasureService) : RetrieveDBMeasuresUseCase {

    override fun executeScanQuery(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryScan())
                .build()
    }

    override fun executeQueryByCategoryAndCreatedDate(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryByCategoryAndCreatedDate())
                .build()
    }

    override fun executeQueryByDate(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryByDate())
                .build()
    }

    override fun executeQueryByPrice(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryByPrice())
                .build()
    }

    override fun executeQueryByCategoryAndPrice(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryByCategoryAndPrice())
                .build()
    }

    override fun executeQueryByCategory(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryByCategory())
                .build()
    }

    override fun executeQueryByRating(): Report {
        return Report.Builder()
                .addMeasure(dbMeasureService.queryByRating())
                .build()
    }
}
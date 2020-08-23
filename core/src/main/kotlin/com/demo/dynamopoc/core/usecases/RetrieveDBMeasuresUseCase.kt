package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.report.Report

interface RetrieveDBMeasuresUseCase {
    fun executeScanQuery(): Report
    fun executeQueryByCategoryAndCreatedDate(): Report
    fun executeQueryByDate(): Report
    fun executeQueryByPrice(): Report
    fun executeQueryByCategoryAndPrice(): Report
    fun executeQueryByCategory(): Report
    fun executeQueryByRating(): Report
}
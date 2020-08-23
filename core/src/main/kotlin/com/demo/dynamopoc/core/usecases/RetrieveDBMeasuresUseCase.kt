package com.demo.dynamopoc.core.usecases

import com.demo.dynamopoc.core.report.Report

interface RetrieveDBMeasuresUseCase {
    fun execute(): Report
}
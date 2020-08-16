package com.demo.dynamopoc.core.report

data class Report(
        var sqlScanQueryTime: Long,
        var noSqlScanQueryTime: Long
)
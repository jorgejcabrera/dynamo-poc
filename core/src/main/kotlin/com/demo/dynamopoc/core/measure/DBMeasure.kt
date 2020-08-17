package com.demo.dynamopoc.core.measure

data class DBMeasure(
        val description: String,
        val sqlQueryTime: Long,
        val noSqlQueryTime: Long
)
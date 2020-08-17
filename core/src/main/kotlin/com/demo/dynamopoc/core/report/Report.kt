package com.demo.dynamopoc.core.report

data class Report(
        var mySqlMeasures: MutableMap<String, Any>,
        var dynamoMeasures: MutableMap<String, Any>
)
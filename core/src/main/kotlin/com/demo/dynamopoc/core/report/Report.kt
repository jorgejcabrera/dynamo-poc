package com.demo.dynamopoc.core.report

import com.demo.dynamopoc.core.measure.DBMeasure

data class Report(var measures: MutableList<DBMeasure>)
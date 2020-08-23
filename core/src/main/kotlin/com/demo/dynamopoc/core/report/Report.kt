package com.demo.dynamopoc.core.report

import com.demo.dynamopoc.core.measure.DBMeasure

class Report private constructor(var measures: MutableList<DBMeasure>) {
    data class Builder(var measures: MutableList<DBMeasure> = mutableListOf()) {
        fun addMeasure(measure: DBMeasure): Builder {
            return apply { this.measures.add(measure) }
        }
        fun build(): Report {
            return Report(measures)
        }
    }
}
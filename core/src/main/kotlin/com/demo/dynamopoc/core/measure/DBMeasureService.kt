package com.demo.dynamopoc.core.measure

interface DBMeasureService {
    fun queryScan(): DBMeasure
    fun queryByDate(): DBMeasure
    fun queryByPrice(): DBMeasure
    fun queryByCategoryAndPrice(): DBMeasure
}
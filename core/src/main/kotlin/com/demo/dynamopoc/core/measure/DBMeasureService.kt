package com.demo.dynamopoc.core.measure

interface DBMeasureService {
    fun queryScan(): DBMeasure
    fun queryByDate(): DBMeasure
    fun queryByPrice(): DBMeasure
    fun queryByCategoryAndPrice(): DBMeasure
    fun queryByCategory(): DBMeasure
    fun queryByRating(): DBMeasure
    fun queryByCategoryAndCreatedDate(): DBMeasure
}
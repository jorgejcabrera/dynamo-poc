package com.demo.dynamopoc.delivery.rest

import com.demo.dynamopoc.core.measure.DBMeasure
import com.demo.dynamopoc.core.report.Report
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasuresUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [RetrieveDBMeasuresController::class])
class RetrieveDBMeasuresControllerTest {

    @MockBean
    lateinit var retrieveDBMeasures: RetrieveDBMeasuresUseCase

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private val report = Report.Builder().addMeasure(DBMeasure("description", 10, 10)).build()

    @BeforeEach
    fun setUp() {
        whenever(retrieveDBMeasures.executeScanQuery()).thenReturn(report)
        whenever(retrieveDBMeasures.executeQueryByCategory()).thenReturn(report)
        whenever(retrieveDBMeasures.executeQueryByCategoryAndCreatedDate()).thenReturn(report)
        whenever(retrieveDBMeasures.executeQueryByCategoryAndPrice()).thenReturn(report)
        whenever(retrieveDBMeasures.executeQueryByPrice()).thenReturn(report)
        whenever(retrieveDBMeasures.executeQueryByDate()).thenReturn(report)
        whenever(retrieveDBMeasures.executeQueryByRating()).thenReturn(report)
    }

    @Test
    fun `when get db measures by scan query then should retrieve a report`() {
        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get("$dbMeasuresPath/scan_query"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
        verify(retrieveDBMeasures, times(1)).executeScanQuery()
    }

    @Test
    fun `when get db measures by category then should retrieve a report`() {
        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get("$dbMeasuresPath/query_by_category"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
        verify(retrieveDBMeasures, times(1)).executeQueryByCategory()
    }

    @Test
    fun `when get db measures by category and created date then should retrieve a report`() {
        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get("$dbMeasuresPath/query_by_category_and_created_date"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
        verify(retrieveDBMeasures, times(1)).executeQueryByCategoryAndCreatedDate()
    }

    @Test
    fun `when get db measures by category and price then should retrieve a report`() {
        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get("$dbMeasuresPath/query_by_category_and_price"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
        verify(retrieveDBMeasures, times(1)).executeQueryByCategoryAndPrice()
    }

    @Test
    fun `when get db measures by price then should retrieve a report`() {
        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get("$dbMeasuresPath/query_by_price"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
        verify(retrieveDBMeasures, times(1)).executeQueryByPrice()
    }

    @Test
    fun `when get db measures by date then should retrieve a report`() {
        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get("$dbMeasuresPath/query_by_date"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
        verify(retrieveDBMeasures, times(1)).executeQueryByDate()
    }

    @Test
    fun `when get db measures by rating then should retrieve a report`() {
        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get("$dbMeasuresPath/query_by_rating"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
        verify(retrieveDBMeasures, times(1)).executeQueryByRating()
    }

    private fun thenReportWasReturned(response: String) {
        val reportReturned = objectMapper.readValue(response, Report::class.java)
        Assertions.assertTrue(reportReturned.measures.isNotEmpty())
    }
}
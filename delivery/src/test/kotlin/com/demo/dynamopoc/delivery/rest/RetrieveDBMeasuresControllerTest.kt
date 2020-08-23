package com.demo.dynamopoc.delivery.rest

import com.demo.dynamopoc.core.measure.DBMeasure
import com.demo.dynamopoc.core.report.Report
import com.demo.dynamopoc.core.usecases.RetrieveDBMeasuresUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
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

    @Test
    fun `when get db measures then should retrieve a report`() {
        // given
        givenAReport()

        // then
        val response = mockMvc.perform(MockMvcRequestBuilders.get(dbMeasuresPath))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString

        thenReportWasReturned(response)
    }

    private fun thenReportWasReturned(response: String) {
        val reportReturned = objectMapper.readValue(response, Report::class.java)
        Assertions.assertTrue(reportReturned.measures.isNotEmpty())
    }

    private fun givenAReport() {
        whenever(retrieveDBMeasures.execute()).thenReturn(report)
    }


}
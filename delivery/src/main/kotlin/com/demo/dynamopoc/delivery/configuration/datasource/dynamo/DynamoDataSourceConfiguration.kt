package com.demo.dynamopoc.delivery.configuration.datasource.dynamo

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBook
import org.slf4j.LoggerFactory

class DynamoDataSourceConfiguration(private val dynamoDBMapper: DynamoDBMapper,
                                    private val amazonDynamoDB: AmazonDynamoDB) {
    fun createSchema() {
        createBookTable()
    }

    private fun createBookTable() {
        try {
            val tableRequest = dynamoDBMapper
                    .generateCreateTableRequest(DynamoBook::class.java)
                    .withProvisionedThroughput(ProvisionedThroughput(40000, 40000))
            tableRequest.globalSecondaryIndexes[0].provisionedThroughput = ProvisionedThroughput(20000, 20000)
            tableRequest.globalSecondaryIndexes[1].provisionedThroughput = ProvisionedThroughput(20000, 20000)
            DynamoDB(amazonDynamoDB)
                    .createTable(tableRequest)
                    .waitForActive()
            LOGGER.info("Table created! [entity={}]", DynamoBook::class.java)
        } catch (ex: ResourceInUseException) {
            LOGGER.warn("Table already exists - skip creation! [entity={}]", DynamoBook::class.java)
        }
    }

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val LOGGER = LoggerFactory.getLogger(javaClass.enclosingClass)
    }
}
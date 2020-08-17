package com.demo.dynamopoc.delivery.configuration.datasource.dynamo

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBook
import com.demo.dynamopoc.infrastructure.dynamo.DynamoBookFactory
import org.slf4j.LoggerFactory

class DynamoSchemaInitializer(private val dynamoDBMapper: DynamoDBMapper,
                              private val amazonDynamoDB: AmazonDynamoDB) {
    fun createSchema() {
        createBookTable()
        initializeWithSomeBooks()
    }

    private fun initializeWithSomeBooks() {
        val factory = DynamoBookFactory()
        repeat(10000) {
            dynamoDBMapper.save(factory.randomBook())
            LOGGER.debug("Creating a new dynamo book...")
        }
    }

    private fun createBookTable() {
        try {
            val tableRequest = dynamoDBMapper
                    .generateCreateTableRequest(DynamoBook::class.java)
                    .withProvisionedThroughput(ProvisionedThroughput(1, 1))
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
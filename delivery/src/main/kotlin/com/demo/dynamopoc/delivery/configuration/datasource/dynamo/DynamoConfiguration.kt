package com.demo.dynamopoc.delivery.configuration.datasource.dynamo

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.demo.dynamopoc.core.book.BookFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DynamoConfiguration {

    @Value("\${amazon.end-point.url}")
    private val dBEndpoint: String? = null

    @Value("\${amazon.region}")
    private val amazonDynamoDBRegion: String? = null


    @Value("\${secret.aws_key}")
    private lateinit var amazonAWSAccessKey: String

    @Value("\${secret.aws_secret_key}")
    private lateinit var amazonAWSSecretKey: String

    @Bean
    fun amazonAWSCredentials(): AWSCredentials? {
        return BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey)
    }

    @Bean
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB): DynamoDBMapper? {
        return DynamoDBMapper(amazonDynamoDB)
    }

    @Bean
    fun amazonDynamoDB(amazonAWSCredentials: AWSCredentials): AmazonDynamoDB? {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(AWSStaticCredentialsProvider(amazonAWSCredentials))
                .withEndpointConfiguration(
                        AwsClientBuilder.EndpointConfiguration(dBEndpoint, amazonDynamoDBRegion)
                )
                .build()
    }

    @Bean
    fun dynamoSchemaInitializer(
            dynamoDBMapper: DynamoDBMapper,
            amazonDynamoDB: AmazonDynamoDB,
            bookFactory: BookFactory
    ): DynamoDataSourceConfiguration {
        return DynamoDataSourceConfiguration(
                dynamoDBMapper,
                amazonDynamoDB
        )
    }
}

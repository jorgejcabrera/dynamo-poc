package com.demo.dynamopoc.delivery.configuration.datasource.mysql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import javax.sql.DataSource

@Configuration
@Import(DataSourceConfiguration::class)
open class HikariDataSourceConfiguration {

    @Bean
    @Autowired
    fun dataSource(properties: DataSourceProperties): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName(properties.driverClassName)
        dataSourceBuilder.url(properties.url)
        dataSourceBuilder.username(properties.username)
        dataSourceBuilder.password(properties.password)
        return dataSourceBuilder.build()
    }
}
package com.demo.dynamopoc.delivery.configuration.datasource.mysql

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class FlyWayConfiguration {

    @Bean
    fun flyway(dataSource: DataSource): Flyway {
        val flyway = Flyway()
        flyway.dataSource = dataSource
        return flyway
    }

    @Bean
    fun flywayInitializer(flyway: Flyway): FlywayMigrationInitializer {
        return FlywayMigrationInitializer(flyway, null)
    }

    @Configuration
    class FlywayInitializerJpaDependencyConfiguration : EntityManagerFactoryDependsOnPostProcessor("flywayInitializer")
}
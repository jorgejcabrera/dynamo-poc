package com.demo.dynamopoc.delivery.configuration.datasource.mysql

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Primary
@Configuration
open class MySqlConfiguration : DataSourceProperties() {

    @Value("\${secret.mysql_usr}")
    private val mySqlUsr: String? = null

    @Value("\${secret.mysql_pwd}")
    private val mySqlPwd: String? = null

    @Value("\${spring.datasource.url}")
    private val mySqlUrl: String? = null

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        this.username = mySqlUsr
        this.url = mySqlUrl
        this.password = mySqlPwd
        super.afterPropertiesSet()
    }
}
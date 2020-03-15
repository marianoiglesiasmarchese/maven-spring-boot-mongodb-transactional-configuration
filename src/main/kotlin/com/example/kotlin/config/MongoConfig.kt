package com.example.kotlin.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
@Profile("!test")
class MongoConfig() : AbstractMongoClientConfiguration() {

    @Value("\${mongodb.prefix:mongodb}")
    private lateinit var prefix: String

    @Value("\${mongodb.host:localhost}")
    private lateinit var host: String

    @Value("\${mongodb.port:}")
    private lateinit var port: String

    @Value("\${mongodb.username:}")
    private lateinit var username: String

    @Value("\${mongodb.password:}")
    private lateinit var password: String

    @Value("\${mongodb.options:}")
    private lateinit var options: String

    @Value("\${mongodb.database}")
    private lateinit var database: String


    override fun mongoClient(): MongoClient {
        val uri = MongoURIBuilder().build(prefix, host, port, username, password, database, options)
        return MongoClients.create(uri)
    }

    override fun getDatabaseName(): String {
        return database
    }

    class MongoURIBuilder() {

        fun build(
            prefix: String,
            host: String,
            port: String,
            username: String,
            password: String,
            database: String,
            options: String
        ): String {
            val optionalCredentials = if (username.isNotEmpty()) "${username}:${password}@" else ""
            val optionalPort = if (port.isNotEmpty()) ":${port}" else ""
            val optionalOptions = if (options.isNotEmpty()) "?${options}" else ""
            return "${prefix}://${optionalCredentials}${host}${optionalPort}/${database}${optionalOptions}"
        }
    }

    @Bean
    fun transactionManager(dbFactory: MongoDbFactory): MongoTransactionManager {
        return MongoTransactionManager(dbFactory)
    }

}
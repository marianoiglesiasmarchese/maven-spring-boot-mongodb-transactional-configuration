package com.example.kotlin.repository

import com.example.kotlin.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

// https://www.baeldung.com/spring-data-jpa-method-in-all-repositories
interface UserRepository : MongoRepository<User, String>, CustomMongoRepository {

    /**
     * For simple queries we can just use Spring Pageable interface,
     * and use the default named queries (Generated Query Methods) provided for spring data to return a Page interface with all the information needed for it.
     */
    override fun findAll(pageable: Pageable): Page<User>

    /**
     * In case of that we need to use an annotated query or aggregation, we can't retrieve a Page<T> because it's not supported by spring,
     * so in that cases we can extend one of the Pageable implementations to cheat the framework to retrieve the information that we need as it were a Page<T>
     * @see <a href="https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries.aggregation"/> mongodb repositories aggregations </a>
     */
    // https://www.baeldung.com/queries-in-spring-data-mongodb
    @Aggregation(
        "{ \$match: { \"address.country\" : \"?0\" } }"
    )
    fun findAllUsersByCountry(country: String, pageable: Pageable): List<User>

    override fun findById(id: String): Optional<User>

}

// https://www.baeldung.com/spring-data-composable-repositories
interface CustomMongoRepository {

    fun customMongoTemplateQuery()

}

class CustomMongoRepositoryImpl(val mongoTemplate: MongoTemplate) : CustomMongoRepository {

    override fun customMongoTemplateQuery() {}

}
package com.example.kotlin.repository

import com.example.kotlin.model.Group
import org.springframework.data.mongodb.repository.MongoRepository

// https://www.baeldung.com/spring-data-jpa-method-in-all-repositories
interface GroupRepository : MongoRepository<Group, String>

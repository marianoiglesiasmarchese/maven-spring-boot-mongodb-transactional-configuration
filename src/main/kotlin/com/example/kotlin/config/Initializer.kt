package com.example.kotlin.config

import com.example.kotlin.model.Address
import com.example.kotlin.model.Group
import com.example.kotlin.model.Name
import com.example.kotlin.model.User
import com.example.kotlin.repository.GroupRepository
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class Initializer() : Logging {

    // https://www.baeldung.com/running-setup-logic-on-startup-in-spring
    @Bean
    fun init(
        userRepository: UserRepository,
        groupRepository: GroupRepository,
        userService: UserService
    ) = CommandLineRunner {

        var user = userRepository.save(
            User(
                name = Name("firstName", "lastName"),
                address = Address(
                    "someDirection",
                    "city",
                    "state",
                    "country",
                    UUID.randomUUID().toString()
                ),
                avatar = "Avatar"
            )
        )

        val group = groupRepository.save(
            Group(name = "A group name")
        )

        /**
         * Addition to group transactional
         */
        user = userService.addToGroup(groupId = group.id!!, user = user)

        logger().info("User with group: $user")

        user = userService.removeFromGroup(groupId = group.id!!, user = user)

        logger().info("User without group: $user")

    }

}

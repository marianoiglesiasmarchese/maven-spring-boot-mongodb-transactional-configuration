package com.example.kotlin.service

import com.example.kotlin.exceptions.GroupNotFoundException
import com.example.kotlin.exceptions.UserNotFoundException
import com.example.kotlin.model.Group
import com.example.kotlin.model.User
import com.example.kotlin.repository.GroupRepository
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.utils.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.domain.Page as SpringPage

@Service
class UserService(
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository
) {

    fun getPaginated(country: String, pageable: Pageable): Page<User> {
        val result = userRepository.findAllUsersByCountry(country, pageable)
        return Page(result, pageable)
    }

    fun findAll(pageable: Pageable): SpringPage<User> {
        return userRepository.findAll(pageable)
    }

    fun getById(id: String): User {
        return userRepository.findById(id).orElseThrow { throw UserNotFoundException() }
    }

    @Transactional
    fun addToGroup(groupId: String, user: User): User {
        val group = groupRepository.findById(groupId).orElseThrow { throw GroupNotFoundException() }
        group.usersCount++
        groupRepository.save(group)
        user.group = group
        return userRepository.save(user)
    }

    @Transactional
    fun removeFromGroup(groupId: String, user: User): User {
        val group = groupRepository.findById(groupId).orElseThrow { throw GroupNotFoundException() }
        group.usersCount--
        groupRepository.save(group)
        user.group = Group(name = "empty group")
        return userRepository.save(user)
    }

}
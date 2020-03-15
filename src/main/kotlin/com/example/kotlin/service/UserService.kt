package com.example.kotlin.service

import com.example.kotlin.exceptions.UserNotFoundException
import com.example.kotlin.model.User
import com.example.kotlin.repository.UserRepository
import com.example.kotlin.utils.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page as SpringPage

@Service
class UserService(private val userRepository: UserRepository) {

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

    //    @Transactional
//    fun somethingTransactional() {
//
//    }

}
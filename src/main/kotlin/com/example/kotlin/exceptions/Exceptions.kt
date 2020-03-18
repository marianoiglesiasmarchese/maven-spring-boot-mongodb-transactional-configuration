package com.example.kotlin.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

// https://www.baeldung.com/spring-response-status-exception
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not Found")
class UserNotFoundException() : RuntimeException()

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Group Not Found")
class GroupNotFoundException() : RuntimeException()
package com.example.kotlin.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface Logging {}

fun <T : Logging> T.logger(): Logger = LoggerFactory.getLogger(javaClass)
package com.example.test3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
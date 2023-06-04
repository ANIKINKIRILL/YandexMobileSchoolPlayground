package com.anikinkirill.playground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
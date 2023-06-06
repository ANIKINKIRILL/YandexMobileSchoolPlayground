package com.anikinkirill.playground.domain

data class UserPostDomain(
    val userId: Int,
    val postId: Int,
    val title: String,
    val body: String,
)

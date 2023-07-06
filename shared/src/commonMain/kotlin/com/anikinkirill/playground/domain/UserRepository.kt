package com.anikinkirill.playground.domain

import com.anikinkirill.playground.domain.posts.UserPostDomain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>

    suspend fun getUserPosts(userId: Int): List<UserPostDomain>
}
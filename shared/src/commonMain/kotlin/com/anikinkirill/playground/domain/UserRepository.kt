package com.anikinkirill.playground.domain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>

    suspend fun getUserPosts(userId: Int): List<UserPostDomain>
}
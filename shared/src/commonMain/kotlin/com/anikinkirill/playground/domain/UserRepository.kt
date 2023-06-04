package com.anikinkirill.playground.domain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>
}
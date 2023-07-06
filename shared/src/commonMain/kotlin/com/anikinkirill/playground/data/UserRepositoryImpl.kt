package com.anikinkirill.playground.data

import com.anikinkirill.playground.domain.UserDomain
import com.anikinkirill.playground.domain.UserRepository

class UserRepositoryImpl(
    private val userMapper: UserMapper,
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getUsers(): List<UserDomain> {
        val remoteResponse = userRemoteDataSource.getUsers()
        return userMapper.map(users = remoteResponse)
    }
}
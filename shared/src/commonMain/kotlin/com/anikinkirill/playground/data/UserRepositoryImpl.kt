package com.anikinkirill.playground.data

import com.anikinkirill.playground.domain.UserDomain
import com.anikinkirill.playground.domain.UserPostDomain
import com.anikinkirill.playground.domain.UserRepository

class UserRepositoryImpl(
    private val userMapper: UserMapper,
    private val postMapper: PostMapper,
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getUsers(): List<UserDomain> {
        val remoteResponse = userRemoteDataSource.getUsers()
        return userMapper.map(users = remoteResponse)
    }

    override suspend fun getUserPosts(userId: Int): List<UserPostDomain> {
        val remoteResponse = userRemoteDataSource.getUserPosts(userId = userId)
        return postMapper.map(posts = remoteResponse)
    }
}
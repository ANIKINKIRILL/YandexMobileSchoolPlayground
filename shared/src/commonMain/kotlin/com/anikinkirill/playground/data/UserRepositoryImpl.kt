package com.anikinkirill.playground.data

import com.anikinkirill.playground.data.posts.PostMapper
import com.anikinkirill.playground.domain.UserDomain
import com.anikinkirill.playground.domain.UserRepository
import com.anikinkirill.playground.domain.posts.UserPostDomain

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
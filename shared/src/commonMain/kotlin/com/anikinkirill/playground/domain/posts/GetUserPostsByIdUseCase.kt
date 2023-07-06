package com.anikinkirill.playground.domain.posts

import com.anikinkirill.playground.domain.UserRepository

class GetUserPostsByIdUseCase(
    private val userRepository: UserRepository,
) {

    suspend fun execute(userId: Int): List<UserPostDomain> {
        return userRepository.getUserPosts(userId = userId)
    }
}
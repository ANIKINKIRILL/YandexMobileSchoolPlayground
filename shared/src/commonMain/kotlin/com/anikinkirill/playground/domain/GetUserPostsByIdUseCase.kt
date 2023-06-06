package com.anikinkirill.playground.domain

class GetUserPostsByIdUseCase(
    private val userRepository: UserRepository,
) {

    @Throws(Exception::class)
    suspend fun execute(userId: Int): List<UserPostDomain> {
        return userRepository.getUserPosts(userId = userId)
    }
}
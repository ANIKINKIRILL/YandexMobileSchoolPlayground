package com.anikinkirill.playground.domain

class GetUsersListUseCase(
    private val usersRepository: UserRepository,
) {

    @Throws(Exception::class)
    suspend fun execute(): List<UserDomain> {
        return usersRepository.getUsers()
    }
}
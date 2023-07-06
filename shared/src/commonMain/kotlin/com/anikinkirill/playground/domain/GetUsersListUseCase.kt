package com.anikinkirill.playground.domain

class GetUsersListUseCase(
    private val usersRepository: UserRepository,
) {

    suspend fun execute(): List<UserDomain> {
        return usersRepository.getUsers()
    }
}
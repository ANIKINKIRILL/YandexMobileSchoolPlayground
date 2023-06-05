package com.anikinkirill.playground.data

import com.anikinkirill.playground.util.Dispatcher
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.withContext

class UserRemoteDataSource(
    private val ktorService: KtorService,
    private val dispatcher: Dispatcher,
) {

    suspend fun getUsers(): List<UserDto> {
        return withContext(dispatcher.io) {
            ktorService.client.get("$BASE_URL/users").body()
        }
    }
}
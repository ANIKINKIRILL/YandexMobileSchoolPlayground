package com.anikinkirill.playground.di

import com.anikinkirill.playground.data.*
import com.anikinkirill.playground.domain.GetUserPostsByIdUseCase
import com.anikinkirill.playground.domain.GetUsersListUseCase
import com.anikinkirill.playground.domain.UserRepository
import com.anikinkirill.playground.util.Dispatcher
import com.anikinkirill.playground.util.provideDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

private fun dataModule() = module {
    factory<UserMapper> {
        UserMapper()
    }

    factory<PostMapper> {
        PostMapper()
    }

    factory<UserRepository> {
        UserRepositoryImpl(
            userMapper = get(),
            userRemoteDataSource = get(),
            postMapper = get(),
        )
    }

    factory<UserRemoteDataSource> {
        UserRemoteDataSource(
            ktorService = get(),
            dispatcher = get(),
        )
    }

    factory<KtorService> {
        KtorService()
    }
}

private fun utilityModule() = module {
    factory<Dispatcher> {
        provideDispatcher()
    }
}

private fun domainModule() = module {
    factory<GetUsersListUseCase> {
        GetUsersListUseCase(
            usersRepository = get(),
        )
    }

    factory<GetUserPostsByIdUseCase> {
        GetUserPostsByIdUseCase(
            userRepository = get()
        )
    }
}

private fun commonModules() = listOf(
    domainModule(),
    dataModule(),
    utilityModule(),
)

fun getCommonModules(): List<Module> {
    return commonModules()
}
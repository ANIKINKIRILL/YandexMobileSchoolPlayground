package com.anikinkirill.playground.util

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    val io: CoroutineDispatcher
}

expect fun provideDispatcher(): Dispatcher
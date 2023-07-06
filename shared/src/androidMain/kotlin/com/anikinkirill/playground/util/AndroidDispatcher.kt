package com.anikinkirill.playground.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class AndroidDispatcher: Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Main
}

actual fun provideDispatcher(): Dispatcher = AndroidDispatcher()
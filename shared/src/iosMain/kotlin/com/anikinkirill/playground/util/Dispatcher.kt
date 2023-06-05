package com.anikinkirill.playground.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IosDispatcher : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}

actual fun provideDispatcher(): Dispatcher = IosDispatcher()
package com.anikinkirill.playground.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun provideDispatcher(): Dispatcher = object : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}
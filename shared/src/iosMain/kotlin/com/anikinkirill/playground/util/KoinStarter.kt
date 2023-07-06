package com.anikinkirill.playground.util

import com.anikinkirill.playground.di.getCommonModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(getCommonModules())
    }
}
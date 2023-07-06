package com.anikinkirill.playground.android

import android.app.Application
import com.anikinkirill.playground.di.getCommonModules
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { 
            modules(getCommonModules())
        }
    }
}
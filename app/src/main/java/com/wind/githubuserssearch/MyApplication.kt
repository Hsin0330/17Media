package com.wind.githubuserssearch

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }

    companion object {
        private var instance: MyApplication? = null

        fun getContext(): Context {
            return instance!!
        }
    }
}
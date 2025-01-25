package com.example.singup_app.application

import android.app.Application
import com.example.singup_app.di.modules.crNFModule
import com.example.singup_app.di.modules.fLModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(crNFModule, fLModule)
        }
    }
}
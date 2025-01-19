package com.gbl.lenenergopoweroutage

import android.app.Application
import com.gbl.lenenergopoweroutage.di.localModule
import com.gbl.lenenergopoweroutage.di.remoteModule
import com.gbl.lenenergopoweroutage.di.repositoryModule
import com.gbl.lenenergopoweroutage.di.useCaseModule
import com.gbl.lenenergopoweroutage.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(remoteModule, localModule, repositoryModule, useCaseModule, uiModule)
        }
    }
}
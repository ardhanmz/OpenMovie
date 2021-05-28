package com.ardhanmz.openmovie

import android.app.Application
import com.ardhanmz.openmovie.di.ApiModule
import com.ardhanmz.openmovie.di.RepositoryModule
import com.ardhanmz.openmovie.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OpenMovie: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OpenMovie)
            modules(listOf(
                ApiModule,
                RepositoryModule,
                ViewModelModule
            ))
        }
    }
}
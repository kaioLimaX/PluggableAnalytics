package com.example.interfaceexemple.di

import android.app.Application
import com.example.interfaceexemple.analytics.facade.Analytics
import com.example.interfaceexemple.analytics.domain.AnalyticsTracker
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, analyticsModule)
        }
        val analyticsTracker : AnalyticsTracker = getKoin().get()
        Analytics.init(analyticsTracker)
    }
}
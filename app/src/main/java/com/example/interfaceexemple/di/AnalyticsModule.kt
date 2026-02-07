package com.example.interfaceexemple.di

import com.example.interfaceexemple.analytics.data.CompositeAnalyticsTracker
import com.example.interfaceexemple.analytics.data.providers.AdjustAnalyticsTracker
import com.example.interfaceexemple.analytics.data.providers.FirebaseAnalyticsTracker
import com.example.interfaceexemple.analytics.domain.AnalyticsTracker
import org.koin.dsl.module

val analyticsModule = module {
    single<AnalyticsTracker> {
        CompositeAnalyticsTracker(
            listOf(
                FirebaseAnalyticsTracker(),
                AdjustAnalyticsTracker()
            )
        )
    }
}
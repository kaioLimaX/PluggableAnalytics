package com.example.interfaceexemple.analytics.domain

import com.example.interfaceexemple.analytics.domain.events.AnalyticsEvent

interface AnalyticsTracker {
    val destination : AnalyticsDestination
    fun track(event: AnalyticsEvent): Result<Unit>
}
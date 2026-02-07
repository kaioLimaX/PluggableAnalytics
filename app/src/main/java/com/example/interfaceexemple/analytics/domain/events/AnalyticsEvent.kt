package com.example.interfaceexemple.analytics.domain.events

import com.example.interfaceexemple.analytics.domain.AnalyticsDestination

sealed class AnalyticsEvent(
    val name : String,
    val destinations : Set<AnalyticsDestination>,
    val params : Map<String, Any> = emptyMap()

)
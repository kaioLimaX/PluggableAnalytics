package com.example.interfaceexemple.analytics.domain.events

import com.example.interfaceexemple.analytics.domain.AnalyticsDestination

sealed class MainAnalyticsEvents (
    name : String,
    params : Map<String, Any> = emptyMap(),
    destinations: Set<AnalyticsDestination>
) : AnalyticsEvent(name,destinations,params){

    data class NameChanged(val newName : String) : MainAnalyticsEvents(
        name = "name_changed",
        destinations = setOf(
            AnalyticsDestination.ADJUST,
            AnalyticsDestination.FIREBASE
        ),
        params = mapOf("new_name" to newName)
    )

    data class IdadeChanged(val idade : Int) : MainAnalyticsEvents(
        name = "idade_changed",
        destinations = setOf(
            AnalyticsDestination.FIREBASE
        ),
        params = mapOf("idade" to idade)
    )

}
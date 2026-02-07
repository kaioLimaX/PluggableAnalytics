package com.example.interfaceexemple.analytics.data.providers

import android.util.Log
import com.example.interfaceexemple.analytics.domain.AnalyticsDestination
import com.example.interfaceexemple.analytics.domain.events.AnalyticsEvent
import com.example.interfaceexemple.analytics.domain.AnalyticsTracker

class AdjustAnalyticsTracker() : AnalyticsTracker {
    override val destination: AnalyticsDestination = AnalyticsDestination.ADJUST

    override fun track(event: AnalyticsEvent): Result<Unit> = runCatching {
        //val adjustEvent = AdjustEvent(event.name)
            event.params.forEach { (key, value) ->
                //adjustEvent.addCallbackParameter(key, value.toString())
                Log.i("Analytics", "Adjust Tracking event: ${event.name} with params: ${event.params} ")
            }

        //Adjust.trackEvent(adjustEvent)
    }
}
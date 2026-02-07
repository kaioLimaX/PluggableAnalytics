package com.example.interfaceexemple.analytics.data

import android.util.Log
import com.example.interfaceexemple.analytics.domain.AnalyticsDestination
import com.example.interfaceexemple.analytics.domain.AnalyticsTracker
import com.example.interfaceexemple.analytics.domain.events.AnalyticsEvent

class CompositeAnalyticsTracker(
    private val trackers: List<AnalyticsTracker>
) : AnalyticsTracker {
    override val destination: AnalyticsDestination
        get() = throw UnsupportedOperationException("CompositeAnalyticsTracker does not have a single destination")
    override fun track(event: AnalyticsEvent): Result<Unit> {
        trackers
            .filter { tracker ->
                event.destinations?.contains(tracker.destination) == true
            }.forEach { tracker ->
            tracker.track(event).onFailure {
                Log.i("AnalyticsError", "track: error tracking event ${event.name} with ${tracker.javaClass.simpleName} : ${it.localizedMessage}" )
            }

        }
        return Result.success(Unit)
    }
}
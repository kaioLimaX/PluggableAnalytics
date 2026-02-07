package com.example.interfaceexemple.analytics.facade

import android.util.Log
import com.example.interfaceexemple.analytics.domain.AnalyticsTracker
import com.example.interfaceexemple.analytics.domain.events.AnalyticsEvent

object Analytics {
    private var tracker: AnalyticsTracker? = null

    fun init(tracker: AnalyticsTracker) {
        this.tracker = tracker
    }

    operator fun invoke(event: AnalyticsEvent) {
        tracker?.track(event)?.onFailure {
            Log.i("Analytics", "invoke: error tracking event ${event.name} : ${it.localizedMessage}" )
        }
    }
}
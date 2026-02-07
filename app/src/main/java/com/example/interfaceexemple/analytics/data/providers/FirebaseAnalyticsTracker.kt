package com.example.interfaceexemple.analytics.data.providers

import android.os.Bundle
import android.util.Log
import com.example.interfaceexemple.analytics.domain.AnalyticsDestination
import com.example.interfaceexemple.analytics.domain.events.AnalyticsEvent
import com.example.interfaceexemple.analytics.domain.AnalyticsTracker

class FirebaseAnalyticsTracker(
    //private val firebase : FirebaseAnalytics
) : AnalyticsTracker{
    override val destination: AnalyticsDestination = AnalyticsDestination.FIREBASE

    override fun track(event: AnalyticsEvent): Result<Unit> = runCatching {
        val bundle = Bundle()
        event.params.forEach { (key,value) ->
            when(value){
                is String -> bundle.putString(key,value)
                is Int -> bundle.putInt(key,value)
                is Long -> bundle.putLong(key,value)
                is Float -> bundle.putFloat(key,value)
                is Double -> bundle.putDouble(key,value)
                is Boolean -> bundle.putBoolean(key,value)
                else -> throw IllegalArgumentException("Unsupported parameter type: ${value::class.java}")
            }
            Log.i("Analytics", "Firebase Tracking event: ${event.name} with params: ${event.params} ")
            //firebase.logEvent(event.name, bundle)
        }
    }

}
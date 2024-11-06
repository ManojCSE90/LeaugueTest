package com.example.leaguetest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LeagueApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
package com.tnurdinov.showcaze

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class ShowCazeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
    }
}
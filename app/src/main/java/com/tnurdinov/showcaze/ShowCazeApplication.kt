package com.tnurdinov.showcaze

import android.app.Activity
import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.tnurdinov.showcaze.di.ApiModule
import com.tnurdinov.showcaze.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ShowCazeApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)

        DaggerAppComponent.builder()
            .application(this)
            .apiModule(ApiModule())
            .build()
            .inject(this)
    }
}
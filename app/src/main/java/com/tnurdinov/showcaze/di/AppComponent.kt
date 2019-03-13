package com.tnurdinov.showcaze.di

import android.app.Application
import com.tnurdinov.showcaze.ShowCazeApplication
import dagger.Component
import dagger.BindsInstance
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, AppModule::class, AndroidSupportInjectionModule::class, ViewModelModule::class, ActivityModule::class, FragmentModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder

        fun build(): AppComponent
    }

    fun inject(appController: ShowCazeApplication)
}
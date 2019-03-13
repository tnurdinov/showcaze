package com.tnurdinov.showcaze.di

import com.tnurdinov.showcaze.ui.content.ContentFragment
import com.tnurdinov.showcaze.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeContentFragment(): ContentFragment

    @ContributesAndroidInjector
    abstract fun contributeImageListFragment(): ListFragment
}
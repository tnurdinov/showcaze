package com.tnurdinov.showcaze.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tnurdinov.showcaze.util.ViewModelFactory
import com.tnurdinov.showcaze.viewmodels.ImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ImageViewModel::class)
    protected abstract fun imageViewModel(viewModel: ImageViewModel): ViewModel
}
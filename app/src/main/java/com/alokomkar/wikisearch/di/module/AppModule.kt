package com.alokomkar.wikisearch.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.alokomkar.wikisearch.ui.ViewModelFactory
import com.alokomkar.wikisearch.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideCryptocurrenciesViewModelFactory(
            factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)

}
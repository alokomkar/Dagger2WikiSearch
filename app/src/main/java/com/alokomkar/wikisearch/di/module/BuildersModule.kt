package com.alokomkar.wikisearch.di.module

import com.alokomkar.wikisearch.ui.MainActivityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityFragment(): MainActivityFragment
}
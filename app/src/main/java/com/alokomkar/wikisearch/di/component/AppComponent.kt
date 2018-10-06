package com.alokomkar.wikisearch.di.component

import com.alokomkar.wikisearch.WikiSearchApplication
import com.alokomkar.wikisearch.di.module.AppModule
import com.alokomkar.wikisearch.di.module.BuildersModule
import com.alokomkar.wikisearch.di.module.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            (AndroidInjectionModule::class),
            (BuildersModule::class),
            (AppModule::class),
            (NetModule::class)]
)
interface AppComponent {
    fun inject(app: WikiSearchApplication)
}
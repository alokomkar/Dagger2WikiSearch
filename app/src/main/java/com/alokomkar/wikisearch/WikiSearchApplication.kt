package com.alokomkar.wikisearch

import android.app.Application
import android.support.v4.app.Fragment
import com.alokomkar.wikisearch.di.component.DaggerAppComponent
import com.alokomkar.wikisearch.di.module.AppModule
import com.alokomkar.wikisearch.di.module.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

@Suppress("DEPRECATION")
class WikiSearchApplication : Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("https://en.wikipedia.org//w/api.php"))
                .build().inject(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

}
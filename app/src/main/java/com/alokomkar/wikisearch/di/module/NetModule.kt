package com.alokomkar.wikisearch.di.module

import com.alokomkar.wikisearch.data.remote.ApiInterface
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun providesInterceptor() : HttpLoggingInterceptor
            = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY )

    @Provides
    @Singleton
    fun providesOkHttpClient( interceptor: HttpLoggingInterceptor ): OkHttpClient
            = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit
            = Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit): ApiInterface
            = retrofit.create(ApiInterface::class.java)
}
package com.alokomkar.wikisearch.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("ticker/")
    fun getSearchResponse(@Query("start") start: String): Observable<Response>
}
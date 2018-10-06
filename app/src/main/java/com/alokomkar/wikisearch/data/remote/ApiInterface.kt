package com.alokomkar.wikisearch.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("api.php" )
    fun getSearchResponse( @QueryMap queryMap : Map<String, String> ): Observable<Response>
}
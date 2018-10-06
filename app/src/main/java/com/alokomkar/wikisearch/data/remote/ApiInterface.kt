package com.alokomkar.wikisearch.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpssearch={searchString}&gpslimit=10")
    fun getSearchResponse( @Path("searchString") searchString: String ): Observable<Response>
}
package com.alokomkar.wikisearch.data

import com.alokomkar.wikisearch.data.remote.ApiInterface
import com.alokomkar.wikisearch.data.remote.Response
import com.alokomkar.wikisearch.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface,
                                     val utils: Utils ) {

    fun getSearchResultsFromApi( searchString : String ) : Observable<Response>
        = apiInterface.getSearchResponse( searchString )


}
package com.alokomkar.wikisearch.data

import com.alokomkar.wikisearch.data.remote.ApiInterface
import com.alokomkar.wikisearch.data.remote.Response
import com.alokomkar.wikisearch.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface,
                                     val utils: Utils ) {

    fun getSearchResultsFromApi( searchString : String ) : Observable<Response>
            = apiInterface.getSearchResponse( getQueryMap( searchString ) )

    private fun getQueryMap(searchString: String): Map<String, String> {
        val map = HashMap<String, String>()
        map["action"] = "query"
        map["format"] = "json"
        map["prop"] = "pageimages%7Cpageterms"
        map["generator"] = "prefixsearch"
        map["redirects"] = "1"
        map["formatversion"] = "2"
        map["piprop"] = "thumbnail"
        map["pithumbsize"] = "50"
        map["pilimit"] = "10"
        map["wbptterms"] = "description"
        map["gpssearch"] = searchString
        map["gpslimit"] = "10"
        return map
    }

}
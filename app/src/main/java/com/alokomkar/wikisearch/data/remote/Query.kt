package com.alokomkar.wikisearch.data.remote

import com.squareup.moshi.Json


data class Query (
        @Json(name ="redirects")
        var redirects: List<Redirect>? = null,
        @Json(name ="pages")
        var pages: List<Page>? = null
)
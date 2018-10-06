package com.alokomkar.wikisearch.data.remote

import com.squareup.moshi.Json


data class Page (
        @Json(name ="pageid")
        var pageid: Int? = null,
        @Json(name ="ns")
        var ns: Int? = null,
        @Json(name ="title")
        var title: String? = null,
        @Json(name ="index")
        var index: Int? = null,
        @Json(name ="thumbnail")
        var thumbnail: Thumbnail? = null,
        @Json(name ="terms")
        var terms: Terms? = null
)
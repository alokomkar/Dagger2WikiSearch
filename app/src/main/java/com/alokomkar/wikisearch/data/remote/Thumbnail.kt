package com.alokomkar.wikisearch.data.remote

import com.squareup.moshi.Json

data class Thumbnail (
        @Json(name ="source")
        var source: String? = null,
        @Json(name ="width")
        var width: Int? = null,
        @Json(name ="height")
        var height: Int? = null
)
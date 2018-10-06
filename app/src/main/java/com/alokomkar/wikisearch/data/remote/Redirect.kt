package com.alokomkar.wikisearch.data.remote

import com.squareup.moshi.Json

data class Redirect (
    @Json(name ="index")
    var index: Int? = null,
    @Json(name ="from")
    var from: String? = null,
    @Json(name ="to")
    var to: String? = null
)
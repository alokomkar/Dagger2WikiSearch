package com.alokomkar.wikisearch.data.remote

import com.squareup.moshi.Json

data class Continue (
    @Json(name = "gpsoffset")
    var gpsoffset: Int? = null,
    @Json(name = "continue")
    var `continue`: String? = null
)
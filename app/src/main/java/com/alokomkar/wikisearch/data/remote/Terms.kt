package com.alokomkar.wikisearch.data.remote

import com.squareup.moshi.Json

data class Terms (
        @Json(name = "description")
        var description: List<String>? = null
)
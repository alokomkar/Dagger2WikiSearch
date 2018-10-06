package com.alokomkar.wikisearch.data.remote

import com.squareup.moshi.Json

data class Response (
        @Json(name ="batchcomplete")
        var batchcomplete: Boolean? = null,
        @Json(name ="continue")
        var `continue`: Continue? = null,
        @Json(name ="query")
        var query: Query? = null
)
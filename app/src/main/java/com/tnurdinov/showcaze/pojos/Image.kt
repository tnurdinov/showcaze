package com.tnurdinov.showcaze.pojos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Image(
    var url: String? = null,
    var width: Int = 0,
    var format: String? = null
)

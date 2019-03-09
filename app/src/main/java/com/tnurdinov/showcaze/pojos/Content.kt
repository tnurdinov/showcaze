package com.tnurdinov.showcaze.pojos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Content(
    var type: String? = null,
    var cols: Int = 0,
    var images: List<Image>? = null,
    var show: Int = 0,
    var title: String? = null,
    var url: String? = null
)
package com.tnurdinov.showcaze.pojos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListResponse(var images: List<Image>)
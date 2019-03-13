package com.tnurdinov.showcaze.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListResponse(var images: List<Image>)
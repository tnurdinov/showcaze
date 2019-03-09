package com.tnurdinov.showcaze.pojos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ContentResponse(var content: List<Content>)
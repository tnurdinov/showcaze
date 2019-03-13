package com.tnurdinov.showcaze.data

import com.tnurdinov.showcaze.data.model.ContentResponse
import com.tnurdinov.showcaze.data.model.ListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {

    @GET("content")
    fun getContent() : Deferred<ContentResponse>

    @GET("list")
    fun getList(): Deferred<ListResponse>

    @GET("{path}")
    fun getList(@Path("path") path: String): Deferred<ListResponse>
}
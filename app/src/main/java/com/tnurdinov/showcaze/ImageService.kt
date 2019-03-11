package com.tnurdinov.showcaze

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tnurdinov.showcaze.pojos.ContentResponse
import com.tnurdinov.showcaze.pojos.ListResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {

    @GET("content")
    fun getContent() : Deferred<ContentResponse>

    @GET("list")
    fun getList(): Deferred<ListResponse>

    @GET("{path}")
    fun getList(@Path("path") path: String): Deferred<ListResponse>

    companion object {
        fun create(): ImageService {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpClient)
                .baseUrl("https://demo4192437.mockable.io/")
                .build()

            return retrofit.create(ImageService::class.java)
        }
    }
}
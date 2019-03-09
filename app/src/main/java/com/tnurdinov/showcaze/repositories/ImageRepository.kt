package com.tnurdinov.showcaze.repositories

import com.tnurdinov.showcaze.ImageService
import com.tnurdinov.showcaze.ScreenState

class ImageRepository {

    private val imageService by lazy {
        ImageService.create()
    }

    suspend fun getContent(): ScreenState {
        return try {
            val list = imageService.getContent().await()
            ScreenState.Data(list.content)
        } catch (exception: Exception) {
            ScreenState.Error
        }
    }
}
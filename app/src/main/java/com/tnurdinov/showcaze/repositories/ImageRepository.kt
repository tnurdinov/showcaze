package com.tnurdinov.showcaze.repositories

import android.net.Uri
import com.tnurdinov.showcaze.ContentScreenState
import com.tnurdinov.showcaze.ImageService
import com.tnurdinov.showcaze.ScreenState
import com.tnurdinov.showcaze.pojos.Image

class ImageRepository {

    private val imageService by lazy {
        ImageService.create()
    }

    suspend fun getContent(): ContentScreenState {
        return try {
            val list = imageService.getContent().await()
            ContentScreenState.Data(list.content)
        } catch (exception: Exception) {
            ContentScreenState.Error(exception.localizedMessage)
        }
    }

    suspend fun getListFromUrl(url: String): List<Image> {
        return try {
            val parse = Uri.parse(url)
            val list = imageService.getList(parse.path!!.replaceFirst("/", "")).await()
            list.images
        } catch (exception: Exception) {
            emptyList()
        }
    }

    suspend fun getImageList(): ScreenState {
        return try {
            val list = imageService.getList().await()
            ScreenState.Data(list.images)
        } catch (exception: Exception) {
            ScreenState.Error
        }
    }
}
package com.tnurdinov.showcaze.repositories

import android.net.Uri
import com.tnurdinov.showcaze.data.ContentScreenState
import com.tnurdinov.showcaze.data.ImageService
import com.tnurdinov.showcaze.data.ListScreenState
import com.tnurdinov.showcaze.data.model.Image
import javax.inject.Singleton

@Singleton
class ImageRepository(private val imageService: ImageService) {

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

    suspend fun getImageList(): ListScreenState {
        return try {
            val list = imageService.getList().await()
            ListScreenState.Data(list.images)
        } catch (exception: Exception) {
            ListScreenState.Error(exception.localizedMessage)
        }
    }
}
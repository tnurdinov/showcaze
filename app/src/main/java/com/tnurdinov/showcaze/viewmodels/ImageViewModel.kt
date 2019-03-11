package com.tnurdinov.showcaze.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tnurdinov.showcaze.ScreenState
import com.tnurdinov.showcaze.pojos.Content
import com.tnurdinov.showcaze.pojos.Image
import com.tnurdinov.showcaze.repositories.ImageRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ImageViewModel: ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val randomMovie: MutableLiveData<List<Content>> = MutableLiveData()
    private val imageList: MutableLiveData<List<Image>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    private val repository by lazy {
        ImageRepository()
    }

    fun getContent() = launch {
        val result = withContext(Dispatchers.IO) {
            repository.getContent()
        }
        when (result) {
            is ScreenState.Data -> randomMovie.postValue(result.someData as List<Content>?)
            is ScreenState.Error -> errorMessage.postValue("Some message")
        }
    }

    fun getFronUrl(url: String) = runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            repository.getListFromUrl(url)
        }
    }

    fun getImageList() = launch {
        val result = withContext(Dispatchers.IO) {
            repository.getImageList()
        }
        when (result) {
            is ScreenState.Data -> imageList.postValue(result.someData as List<Image>?)
            is ScreenState.Error -> errorMessage.postValue("Some message")
        }
    }

    fun observeMovieDetails(): LiveData<List<Content>> {
        return randomMovie
    }

    fun observeImageList(): LiveData<List<Image>> {
        return imageList
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

}
package com.tnurdinov.showcaze.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tnurdinov.showcaze.ContentScreenState
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

    private val contentList: MutableLiveData<List<Content>> = MutableLiveData()
    private val imageList: MutableLiveData<List<Image>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()

    private val repository by lazy {
        ImageRepository()
    }

    init {
        loading.postValue(false)
    }

    fun getContent() = launch {
        loading.postValue(true)
        val result = withContext(Dispatchers.IO) {
            repository.getContent()
        }
        when (result) {
            is ContentScreenState.Data -> {
                for (content in result.contentList) {
                    content.url?.let { urlStr ->
                        content.images = getImgListFromUrl(urlStr)
                    }
                }
                contentList.postValue(result.contentList)
                loading.postValue(false)
            }
            is ContentScreenState.Error -> {
                errorMessage.postValue(result.error)
                loading.postValue(false)
            }
        }
    }

    private fun getImgListFromUrl(url: String) = runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            repository.getListFromUrl(url)
        }
    }

    fun getImageList() = launch {
        loading.postValue(true)
        val result = withContext(Dispatchers.IO) {
            repository.getImageList()
        }
        when (result) {
            is ScreenState.Data -> {
                imageList.postValue(result.someData as List<Image>?)
                loading.postValue(false)
            }
            is ScreenState.Error -> {
                errorMessage.postValue("Some message")
                loading.postValue(false)
            }
        }
    }

    fun observeMovieDetails(): LiveData<List<Content>> {
        return contentList
    }

    fun observeImageList(): LiveData<List<Image>> {
        return imageList
    }

    fun observeLoadingState(): LiveData<Boolean> {
        return loading
    }

    fun observeErrorMessage(): LiveData<String> {
        return errorMessage
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

}
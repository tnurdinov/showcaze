package com.tnurdinov.showcaze.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tnurdinov.showcaze.ScreenState
import com.tnurdinov.showcaze.pojos.Content
import com.tnurdinov.showcaze.repositories.ImageRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ImageViewModel: ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val randomMovie: MutableLiveData<List<Content>> = MutableLiveData()
    private val errorMessage: MutableLiveData<String> = MutableLiveData()

    private val repository by lazy {
        ImageRepository()
    }

    fun getContent() = launch {
        val result = withContext(Dispatchers.IO) {
            repository.getContent()
        }
        when (result) {
            is ScreenState.Data -> randomMovie.postValue(result.someData)
            is ScreenState.Error -> errorMessage.postValue("Some message")
        }
    }

    fun observeMovieDetails(): LiveData<List<Content>> {
        return randomMovie
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

}
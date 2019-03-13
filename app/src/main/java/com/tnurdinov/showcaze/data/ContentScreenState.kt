package com.tnurdinov.showcaze.data

import com.tnurdinov.showcaze.data.model.Content

sealed class ContentScreenState {
    data class Error(val error: String) : ContentScreenState()
    data class Data(val contentList: List<Content>) : ContentScreenState()
}
package com.tnurdinov.showcaze

import com.tnurdinov.showcaze.pojos.Content

sealed class ContentScreenState {
    data class Error(val error: String) : ContentScreenState()
    data class Data(val contentList: List<Content>) : ContentScreenState()
}
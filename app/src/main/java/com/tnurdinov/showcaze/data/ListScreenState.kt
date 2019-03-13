package com.tnurdinov.showcaze.data

import com.tnurdinov.showcaze.data.model.Image

sealed class ListScreenState {
    data class Error(val error: String) : ListScreenState()
    data class Data(val imageList: List<Image>) : ListScreenState()
}
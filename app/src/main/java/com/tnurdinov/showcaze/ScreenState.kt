package com.tnurdinov.showcaze

import com.tnurdinov.showcaze.pojos.Content

sealed class ScreenState {
    object Error : ScreenState()
    data class Data(val someData: List<Content>) : ScreenState()
}
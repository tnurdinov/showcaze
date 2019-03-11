package com.tnurdinov.showcaze

sealed class ScreenState {
    object Error : ScreenState()
    data class Data(val someData: List<*>) : ScreenState()
}
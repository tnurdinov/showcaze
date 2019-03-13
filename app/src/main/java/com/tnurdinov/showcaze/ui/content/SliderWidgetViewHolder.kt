package com.tnurdinov.showcaze.ui.content

import android.annotation.SuppressLint
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tnurdinov.showcaze.pojos.Content
import com.tnurdinov.showcaze.pojos.Image
import java.util.*

class SliderWidgetViewHolder(private val viewPager2: ViewPager2) : RecyclerView.ViewHolder(viewPager2) {

    private val handler = Handler()
    lateinit var runnable: Runnable

    fun bind(content: Content) {
        viewPager2.adapter = ViewPagerAdapter(content.images as ArrayList<Image>)

        runnable = Runnable {
            viewPager2.currentItem = when (viewPager2.currentItem) {
                (content.images as ArrayList<Image>).lastIndex -> 0
                else -> viewPager2.currentItem + 1
            }
        }

        handler.postDelayed(runnable, 3000)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SwitchIntDef")
            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    ViewPager2.SCROLL_STATE_DRAGGING,
                    ViewPager2.SCROLL_STATE_SETTLING -> handler.removeCallbacks(runnable)
                    ViewPager2.SCROLL_STATE_IDLE -> handler.postDelayed(runnable, 3000)
                }
            }
        })

    }
}
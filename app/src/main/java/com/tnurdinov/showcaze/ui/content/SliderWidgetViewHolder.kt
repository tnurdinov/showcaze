package com.tnurdinov.showcaze.ui.content

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tnurdinov.showcaze.pojos.Content
import java.util.*

class SliderWidgetViewHolder(private val viewGroup: ViewPager2) : RecyclerView.ViewHolder(viewGroup) {

    fun bind(content: Content) {
        viewGroup.adapter = ViewPagerAdapter(ArrayList(content.images))
        viewGroup.currentItem = 1
        val timer = Timer()

//        viewGroup.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                timer.cancel()
//            }
//
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//            }
//        })


        val handler = Handler()
        val runnable = Runnable {
            viewGroup.currentItem += 1
        }

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 0, 3000)
    }
}
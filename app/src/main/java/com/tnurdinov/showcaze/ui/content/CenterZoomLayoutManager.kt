package com.tnurdinov.showcaze.ui.content

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CenterZoomLayoutManager(context: Context, orientation: Int, reverseLayout:Boolean): LinearLayoutManager(context, orientation, reverseLayout) {
    private val mShrinkAmount = 0.15f
    private val mShrinkDistance = 0.9f

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        if (orientation == HORIZONTAL) {

            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            val midpoint = width / 2.0f
            val d0 = 0.0f
            val d1 = mShrinkDistance * midpoint
            val s0 = 1.0f
            val s1 = 1.0f - mShrinkAmount
            for (i in 0 until childCount) {
                val child = getChildAt(i)!!
                val childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2.0f
                val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.scaleX = scale
                child.scaleY = scale
            }
            return scrolled
        } else return 0
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        scrollHorizontallyBy(0, recycler, state)
    }
}
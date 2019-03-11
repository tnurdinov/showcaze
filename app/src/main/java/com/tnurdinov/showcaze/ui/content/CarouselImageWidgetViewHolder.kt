package com.tnurdinov.showcaze.ui.content

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.pojos.Content


class CarouselImageWidgetViewHolder(private val recyclerView: RecyclerView) : RecyclerView.ViewHolder(recyclerView) {
    private lateinit var viewAdapter: CarouselAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    fun bind(content: Content) {
        viewManager = CenterZoomLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        viewAdapter = CarouselAdapter(content.images!!)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewAdapter.notifyDataSetChanged()
    }

}
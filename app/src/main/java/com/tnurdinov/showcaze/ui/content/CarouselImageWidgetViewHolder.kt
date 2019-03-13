package com.tnurdinov.showcaze.ui.content

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.pojos.Content

class CarouselImageWidgetViewHolder(
    recyclerView: RecyclerView,
    listener: OnItemClickListener
) : RecyclerView.ViewHolder(recyclerView) {
    private var viewAdapter: CarouselAdapter
    private var viewManager: RecyclerView.LayoutManager

    init {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        viewManager = CenterZoomLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        viewAdapter = CarouselAdapter(listener = listener)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun bind(content: Content) {
        content.images?.let { imgList ->
            viewAdapter.images.clear()
            viewAdapter.images.addAll(imgList)
            viewAdapter.notifyDataSetChanged()
        }
    }
}
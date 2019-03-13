package com.tnurdinov.showcaze.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.data.model.Image

class ImageListAdapter: RecyclerView.Adapter<ListItemViewHolder>() {
    var listDataSet: List<Image> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val viewGroup = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_image, parent, false) as ConstraintLayout
        return ListItemViewHolder(viewGroup)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(listDataSet[position])
    }

    override fun getItemCount() = listDataSet.size

    fun setContent(contents: List<Image>) {
        listDataSet = contents
    }
}
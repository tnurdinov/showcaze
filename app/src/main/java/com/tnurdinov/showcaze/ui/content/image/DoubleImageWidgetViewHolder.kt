package com.tnurdinov.showcaze.ui.content.image

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.data.model.Content
import com.tnurdinov.showcaze.ui.content.OnItemClickListener

class DoubleImageWidgetViewHolder (
    viewGroup: ViewGroup,
    listener: OnItemClickListener
) : RecyclerView.ViewHolder(viewGroup) {

    var firstImageView: AppCompatImageView = itemView.findViewById(R.id.firstImageView)
    var secondImageView: AppCompatImageView = itemView.findViewById(R.id.secondImageView)

    init {
        itemView.setOnClickListener {
            listener.onItemClick()
        }
    }

    fun bind(content: Content) {
        Picasso.get().load(content.images?.get(0)?.url).into(firstImageView)
        Picasso.get().load(content.images?.get(1)?.url).into(secondImageView)
    }
}
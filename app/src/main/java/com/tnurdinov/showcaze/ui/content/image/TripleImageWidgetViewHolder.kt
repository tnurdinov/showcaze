package com.tnurdinov.showcaze.ui.content.image

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Content
import com.tnurdinov.showcaze.ui.content.OnItemClickListener

class TripleImageWidgetViewHolder (
    viewGroup: ViewGroup,
    listener: OnItemClickListener
) : RecyclerView.ViewHolder(viewGroup) {

    private val firstImageView: AppCompatImageView = itemView.findViewById(R.id.firstImageView)
    private val secondImageView: AppCompatImageView = itemView.findViewById(R.id.secondImageView)
    private val thirdImageView: AppCompatImageView = itemView.findViewById(R.id.thirdImageView)

    init {
        itemView.setOnClickListener {
            listener.onItemClick()
        }
    }

    fun bind(content: Content) {
        Picasso.get().load(content.images?.get(0)?.url).into(firstImageView)
        Picasso.get().load(content.images?.get(1)?.url).into(secondImageView)
        Picasso.get().load(content.images?.get(2)?.url).into(thirdImageView)
    }
}
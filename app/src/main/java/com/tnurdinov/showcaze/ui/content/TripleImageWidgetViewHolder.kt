package com.tnurdinov.showcaze.ui.content

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Content

class TripleImageWidgetViewHolder (viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup) {

    private val firstImageView: AppCompatImageView = itemView.findViewById(R.id.firstImageView)
    private val secondImageView: AppCompatImageView = itemView.findViewById(R.id.secondImageView)
    private val thirdImageView: AppCompatImageView = itemView.findViewById(R.id.thirdImageView)

    fun bind(content: Content) {
        Picasso.get().load(content.images?.get(0)?.url).into(firstImageView)
        Picasso.get().load(content.images?.get(1)?.url).into(secondImageView)
        Picasso.get().load(content.images?.get(2)?.url).into(thirdImageView)
    }
}
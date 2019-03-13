package com.tnurdinov.showcaze.ui.content

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Content


class SingleImageWidgetViewHolder (
    viewGroup: ViewGroup,
    listener: OnItemClickListener
) : RecyclerView.ViewHolder(viewGroup) {

    var singleImageView: AppCompatImageView = itemView.findViewById(R.id.singleImageView)

    init {
        itemView.setOnClickListener {
            listener.onItemClick()
        }
    }

    fun bind(content: Content) {
        Picasso.get().load(content.images?.first()?.url).into(singleImageView)
    }

}
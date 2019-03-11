package com.tnurdinov.showcaze.ui.content

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Content


class ImageWidgetViewHolder (viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup) {
    var singleImageView: ImageView = itemView.findViewById(R.id.singleImageView)

    fun bind(content: Content) {
        Picasso.get().load(content.images?.first()?.url).into(singleImageView)
    }

}
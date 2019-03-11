package com.tnurdinov.showcaze.ui.list

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Image

class ListItemViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup) {
    var imageView: AppCompatImageView = itemView.findViewById(R.id.listImageView)

    fun bind(image: Image) {
        Picasso.get().load(image.url).into(imageView)
    }
}
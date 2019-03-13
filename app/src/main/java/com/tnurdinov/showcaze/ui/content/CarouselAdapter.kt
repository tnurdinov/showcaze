package com.tnurdinov.showcaze.ui.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Image

class CarouselAdapter(val images: MutableList<Image> = mutableListOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselVH =
        CarouselVH(LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = holder.itemView.run {
        findViewById<AppCompatImageView>(R.id.carouselImageView).let { imageView ->
            Picasso.get().load(images[position].url).into(imageView)
        }
    }

    override fun getItemCount() = images.size
}

class CarouselVH(itemView: View) : RecyclerView.ViewHolder(itemView)

package com.tnurdinov.showcaze.ui.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Image

class CarouselAdapter(private val images: List<Image>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselVH =
        CarouselVH(LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = holder.itemView.run {
        val imageView = findViewById<AppCompatImageView>(R.id.sliderImageView)
        Picasso.get().load(images[position].url).into(imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}

class CarouselVH(itemView: View) : RecyclerView.ViewHolder(itemView)

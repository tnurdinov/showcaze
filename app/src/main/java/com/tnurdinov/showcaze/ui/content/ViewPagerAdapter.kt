package com.tnurdinov.showcaze.ui.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Image

class ViewPagerAdapter(private val images: ArrayList<Image>) : RecyclerView.Adapter<PagerVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_slider_page, parent, false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        val imageView = findViewById<AppCompatImageView>(R.id.sliderImageView)
        Picasso.get().load(images[position].url).into(imageView)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
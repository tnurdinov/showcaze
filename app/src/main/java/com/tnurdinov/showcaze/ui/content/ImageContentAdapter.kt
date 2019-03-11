package com.tnurdinov.showcaze.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Content

class ImageContentAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var contentDataset: List<Content> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            SINGLE_IMAGE_WIDGET -> {
                val constraintLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.single_image_widget, parent, false) as ConstraintLayout
                SingleImageWidgetViewHolder(constraintLayout)
            }
            DOUBLE_IMAGE_WIDGET -> {
                val constraintLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.double_image_widget, parent, false) as ConstraintLayout
                DoubleImageWidgetViewHolder(constraintLayout)
            }
            TRIPLE_IMAGE_WIDGET -> {
                val constraintLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.triple_image_widget, parent, false) as ConstraintLayout
                TripleImageWidgetViewHolder(constraintLayout)
            }
            SLIDER_WIDGET -> {
                val viewPager = LayoutInflater.from(parent.context)
                    .inflate(R.layout.slider_widget, parent, false) as ViewPager2
                SliderWidgetViewHolder(viewPager)
            }
            CAROUSEL_WIDGET -> {
                val recyclerView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.carousel_widget, parent, false) as RecyclerView
                CarouselImageWidgetViewHolder(recyclerView)
            }
            else -> {
                val constraintLayout2 = LayoutInflater.from(parent.context)
                    .inflate(R.layout.single_image_widget, parent, false) as ConstraintLayout
                SampleViewHolder(constraintLayout2)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType) {
            SINGLE_IMAGE_WIDGET -> {
                val imageViewHolder = holder as SingleImageWidgetViewHolder
                imageViewHolder.bind(contentDataset[position])
            }
            DOUBLE_IMAGE_WIDGET -> {
                val imageViewHolder = holder as DoubleImageWidgetViewHolder
                imageViewHolder.bind(contentDataset[position])
            }
            TRIPLE_IMAGE_WIDGET -> {
                val imageViewHolder = holder as TripleImageWidgetViewHolder
                imageViewHolder.bind(contentDataset[position])
            }
            SLIDER_WIDGET -> {
                val imageViewHolder = holder as SliderWidgetViewHolder
                imageViewHolder.bind(contentDataset[position])
            }
            CAROUSEL_WIDGET -> {
                val imageViewHolder = holder as CarouselImageWidgetViewHolder
                imageViewHolder.bind(contentDataset[position])
            }
            else -> {
                val sampleViewHolder = holder as SampleViewHolder
                sampleViewHolder.bind(contentDataset[position])
            }
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick()
        }
    }

    override fun getItemCount() = contentDataset.size

    fun setContent(contents: List<Content>) {
        contentDataset = contents
    }

    override fun getItemViewType(position: Int): Int {
        return when(contentDataset[position].type) {
            IMAGE_WIDGET -> when(contentDataset[position].cols) {
                1 -> SINGLE_IMAGE_WIDGET
                2 -> DOUBLE_IMAGE_WIDGET
                3 -> TRIPLE_IMAGE_WIDGET
                else -> SINGLE_IMAGE_WIDGET
            }
            "slider_widget" -> SLIDER_WIDGET
            "carousel_widget" -> CAROUSEL_WIDGET
            else -> UNKNOWN_WIDGET
        }
    }

    companion object {
        const val SINGLE_IMAGE_WIDGET = 0
        const val DOUBLE_IMAGE_WIDGET = 1
        const val TRIPLE_IMAGE_WIDGET = 2
        const val SLIDER_WIDGET = 3
        const val CAROUSEL_WIDGET = 4
        const val UNKNOWN_WIDGET = -1

        const val IMAGE_WIDGET = "image_widget"
    }
}
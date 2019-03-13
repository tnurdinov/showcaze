package com.tnurdinov.showcaze.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Content

class ImageContentAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val contentDataList = mutableListOf<Content>()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            SINGLE_IMAGE_WIDGET -> {
                val constraintLayout = inflater
                    .inflate(R.layout.single_image_widget, parent, false) as ConstraintLayout
                SingleImageWidgetViewHolder(constraintLayout, listener)
            }
            DOUBLE_IMAGE_WIDGET -> {
                val constraintLayout = inflater
                    .inflate(R.layout.double_image_widget, parent, false) as ConstraintLayout
                DoubleImageWidgetViewHolder(constraintLayout, listener)
            }
            TRIPLE_IMAGE_WIDGET -> {
                val constraintLayout = inflater
                    .inflate(R.layout.triple_image_widget, parent, false) as ConstraintLayout
                TripleImageWidgetViewHolder(constraintLayout, listener)
            }
            SLIDER_WIDGET -> {
                val viewPager = inflater
                    .inflate(R.layout.slider_widget, parent, false) as ViewPager2
                SliderWidgetViewHolder(viewPager, listener)
            }
            CAROUSEL_WIDGET -> {
                val recyclerView = inflater
                    .inflate(R.layout.carousel_widget, parent, false) as RecyclerView
                CarouselImageWidgetViewHolder(recyclerView, listener)
            }
            else -> {
                val constraintLayout2 = inflater
                    .inflate(R.layout.single_image_widget, parent, false) as ConstraintLayout
                SampleViewHolder(constraintLayout2)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder.itemViewType) {
            SINGLE_IMAGE_WIDGET -> {
                val imageViewHolder = holder as SingleImageWidgetViewHolder
                imageViewHolder.bind(contentDataList[position])
            }
            DOUBLE_IMAGE_WIDGET -> {
                val imageViewHolder = holder as DoubleImageWidgetViewHolder
                imageViewHolder.bind(contentDataList[position])
            }
            TRIPLE_IMAGE_WIDGET -> {
                val imageViewHolder = holder as TripleImageWidgetViewHolder
                imageViewHolder.bind(contentDataList[position])
            }
            SLIDER_WIDGET -> {
                val imageViewHolder = holder as SliderWidgetViewHolder
                imageViewHolder.bind(contentDataList[position])
            }
            CAROUSEL_WIDGET -> {
                val imageViewHolder = holder as CarouselImageWidgetViewHolder
                imageViewHolder.bind(contentDataList[position])
            }
            else -> {
                val sampleViewHolder = holder as SampleViewHolder
                sampleViewHolder.bind(contentDataList[position])
            }
        }

//        holder.ite.setOnClickListener {
//            listener.onItemClick()
//        }
    }

    override fun getItemCount() = contentDataList.size

    override fun getItemViewType(position: Int): Int {
        return when (contentDataList[position].type) {
            IMAGE_WIDGET_TYPE -> when (contentDataList[position].cols) {
                ONE -> SINGLE_IMAGE_WIDGET
                TWO -> DOUBLE_IMAGE_WIDGET
                THREE -> TRIPLE_IMAGE_WIDGET
                else -> SINGLE_IMAGE_WIDGET
            }
            SLIDER_WIDGET_TYPE -> SLIDER_WIDGET
            CAROUSEL_WIDGET_TYPE -> CAROUSEL_WIDGET
            else -> UNKNOWN_WIDGET
        }
    }

    companion object {
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3

        const val SINGLE_IMAGE_WIDGET = 0
        const val DOUBLE_IMAGE_WIDGET = 1
        const val TRIPLE_IMAGE_WIDGET = 2
        const val SLIDER_WIDGET = 3
        const val CAROUSEL_WIDGET = 4
        const val UNKNOWN_WIDGET = -1

        const val IMAGE_WIDGET_TYPE = "image_widget"
        const val SLIDER_WIDGET_TYPE = "slider_widget"
        const val CAROUSEL_WIDGET_TYPE = "carousel_widget"
    }
}
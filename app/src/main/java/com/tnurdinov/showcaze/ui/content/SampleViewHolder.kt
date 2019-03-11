package com.tnurdinov.showcaze.ui.content

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.pojos.Content

class SampleViewHolder(var viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup) {
    var textView: TextView = TextView(itemView.context)

    init {
        textView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun bind(content: Content) {
        textView.parent?.let {
            viewGroup.removeView(textView)
        }
        textView.text = "Not implemeted yet"
        viewGroup.addView(textView)
    }
}
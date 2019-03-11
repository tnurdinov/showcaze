package com.tnurdinov.showcaze.ui.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.extensions.lazyUnsynchronized
import com.tnurdinov.showcaze.pojos.Content
import com.tnurdinov.showcaze.viewmodels.ImageViewModel


class ContentFragment : Fragment() {
    private lateinit var viewAdapter: ImageContentAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val contentRv: RecyclerView by lazyUnsynchronized {
        view!!.findViewById<RecyclerView>(R.id.content_recycler_view)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ImageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getContent()
        observeMovieDetail()

        viewManager = LinearLayoutManager(activity)
        viewAdapter = ImageContentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentRv.apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(context, R.drawable.line_divider)?.let { dividerItemDecoration.setDrawable(it) }
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun observeMovieDetail() {
        val observer = Observer<List<Content>> { contents ->
            for (content in contents) {
                content.url?.let {
                    val fromUrl = viewModel.getFronUrl(it)
                    content.images = fromUrl
                }
            }
            viewAdapter.setContent(contents)
            viewAdapter.notifyDataSetChanged()
        }
        viewModel.observeMovieDetails().observe(this, observer)
    }
}

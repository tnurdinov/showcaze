package com.tnurdinov.showcaze.ui.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Content
import com.tnurdinov.showcaze.viewmodels.ImageViewModel


class ContentFragment : Fragment(), OnItemClickListener {
    private lateinit var viewAdapter: ImageContentAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ImageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewAdapter = ImageContentAdapter(this)
        observeMovieDetail()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_content, container, false)
        view.findViewById<RecyclerView>(R.id.content_recycler_view).apply {
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(context, R.drawable.line_divider)?.let { dividerItemDecoration.setDrawable(it) }
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = viewAdapter
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getContent()
    }

    private fun observeMovieDetail() {
        val observer = Observer<List<Content>> { contents ->
            for (content in contents) {
                content.url?.let {
                    val fromUrl = viewModel.getFronUrl(it)
                    content.images = fromUrl
                }
            }
            viewAdapter.contentDataList.clear()
            viewAdapter.contentDataList.addAll(contents)
            viewAdapter.notifyDataSetChanged()
        }
        viewModel.observeMovieDetails().observe(this, observer)
    }

    override fun onItemClick() {
        Navigation.findNavController(view!!).navigate(R.id.next_action)
    }
}

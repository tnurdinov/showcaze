package com.tnurdinov.showcaze.ui.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.data.model.Content
import com.tnurdinov.showcaze.viewmodels.ImageViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class ContentFragment : Fragment(), OnItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewAdapter: ImageContentAdapter
    private lateinit var progressView: FrameLayout
    lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImageViewModel::class.java)
        viewAdapter = ImageContentAdapter(this)
        observeContentList()
        observeLoadingState()
        observeErrorMessage()
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
        progressView = view.findViewById(R.id.progress_view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getContent()
    }

    private fun observeContentList() {
        val observer = Observer<List<Content>> { contents ->
            viewAdapter.contentDataList.clear()
            viewAdapter.contentDataList.addAll(contents)
            viewAdapter.notifyDataSetChanged()
        }
        viewModel.observeMovieDetails().observe(this, observer)
    }

    private fun observeLoadingState() {
        val observer = Observer<Boolean> { loading ->
            when(loading) {
                true -> progressView.visibility = VISIBLE
                false -> progressView.visibility = GONE
            }
        }
        viewModel.observeLoadingState().observe(this, observer)
    }

    private fun observeErrorMessage() {
        val observer = Observer<String> { msg ->
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }
        viewModel.observeErrorMessage().observe(this, observer)
    }

    override fun onItemClick() {
        Navigation.findNavController(view!!).navigate(R.id.next_action)
    }
}

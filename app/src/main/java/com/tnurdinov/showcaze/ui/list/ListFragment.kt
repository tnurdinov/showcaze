package com.tnurdinov.showcaze.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.pojos.Image
import com.tnurdinov.showcaze.viewmodels.ImageViewModel

class ListFragment : Fragment() {
    private lateinit var viewAdapter: ImageListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var progressView: FrameLayout

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ImageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewManager = GridLayoutManager(activity, 2)
        viewAdapter = ImageListAdapter()
        observeImageList()
        observeLoadingState()
        observeErrorMessage()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.findViewById<RecyclerView>(R.id.list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        progressView = view.findViewById(R.id.progress_view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getImageList()
    }

    private fun observeImageList() {
        val observer = Observer<List<Image>> { images ->
            viewAdapter.setContent(images)
            viewAdapter.notifyDataSetChanged()
        }
        viewModel.observeImageList().observe(this, observer)
    }

    private fun observeLoadingState() {
        val observer = Observer<Boolean> { loading ->
            when(loading) {
                true -> progressView.visibility = View.VISIBLE
                false -> progressView.visibility = View.GONE
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
}

package com.tnurdinov.showcaze.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tnurdinov.showcaze.R
import com.tnurdinov.showcaze.extensions.lazyUnsynchronized
import com.tnurdinov.showcaze.pojos.Image
import com.tnurdinov.showcaze.viewmodels.ImageViewModel

class ListFragment : Fragment() {
    private lateinit var viewAdapter: ImageListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val contentRv: RecyclerView by lazyUnsynchronized {
        view!!.findViewById<RecyclerView>(R.id.list_recycler_view)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ImageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewManager = GridLayoutManager(activity, 2)
        viewAdapter = ImageListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentRv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getImageList()
        observeImageList()
    }


    private fun observeImageList() {
        val observer = Observer<List<Image>> { images ->
            viewAdapter.setContent(images)
            viewAdapter.notifyDataSetChanged()
        }
        viewModel.observeImageList().observe(this, observer)
    }
}

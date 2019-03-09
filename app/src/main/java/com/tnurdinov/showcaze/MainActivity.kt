package com.tnurdinov.showcaze

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tnurdinov.showcaze.pojos.Content
import com.tnurdinov.showcaze.viewmodels.ImageViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ImageViewModel by lazy {
        ViewModelProviders.of(this).get(ImageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getContent()
        observeMovieDetail()
    }

    private fun observeMovieDetail() {
        val observer = Observer<List<Content>> { content ->
            Log.d("TAG", content.get(0).type)
        }
        viewModel.observeMovieDetails().observe(this, observer)
    }
}

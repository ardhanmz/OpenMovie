package com.ardhanmz.openmovie.ui.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.FragmentGenreBinding
import com.ardhanmz.openmovie.databinding.FragmentVideoBinding
import com.ardhanmz.openmovie.ui.adapter.GenreAdapter
import com.ardhanmz.openmovie.ui.adapter.VideoAdapter
import com.ardhanmz.openmovie.ui.genre.GenreViewModel
import com.ardhanmz.openmovie.ui.genre.GenreViewModelFactory
import org.koin.android.ext.android.inject

class VideoFragment : Fragment() {

    private val viewModelFactory : VideoViewModelFactory by inject()
    private lateinit var viewModel: VideoViewModel
    private lateinit var binding: FragmentVideoBinding
    val safeArgs : VideoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false)
        viewModel = ViewModelProvider(this,viewModelFactory).get(VideoViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        loadData(binding)
        return binding.root
    }

    private fun loadData(binding: FragmentVideoBinding?) {
        val linearLayoutManager = LinearLayoutManager(
            this.context, RecyclerView.VERTICAL,false)
        binding?.rvVideo?.layoutManager = linearLayoutManager
        viewModel.getListVideo(safeArgs.idMovies)
        viewModel.videoList.observe(viewLifecycleOwner, Observer { videos ->
            binding?.rvVideo?.adapter = VideoAdapter(videos)
        })
    }


}
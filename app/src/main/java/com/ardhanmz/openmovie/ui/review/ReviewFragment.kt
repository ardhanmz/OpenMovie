package com.ardhanmz.openmovie.ui.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.FragmentDiscoverGenreBinding
import com.ardhanmz.openmovie.databinding.FragmentReviewBinding
import com.ardhanmz.openmovie.ui.adapter.DiscoverAdapter
import com.ardhanmz.openmovie.ui.adapter.ReviewAdapter
import com.ardhanmz.openmovie.ui.discovergenre.DiscoverGenreFragmentArgs
import com.ardhanmz.openmovie.ui.discovergenre.DiscoverGenreViewModel
import com.ardhanmz.openmovie.ui.discovergenre.DiscoverGenreViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ReviewFragment : Fragment() {
    private val viewModelFactory : ReviewViewModelFactory by inject()
    private lateinit var viewModel : ReviewViewModel
    private lateinit var binding : FragmentReviewBinding
    lateinit var adapter: ReviewAdapter
    val args : ReviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ReviewViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        loadData(binding)
        return binding.root
    }

    private fun loadData(binding: FragmentReviewBinding?) {
        val linearLayoutManager = LinearLayoutManager(
            this.context, RecyclerView.VERTICAL,false)
        binding?.rvReview?.layoutManager = linearLayoutManager
        adapter = ReviewAdapter()
        binding?.rvReview?.adapter = adapter
        val idMovie = args.idMovie
        lifecycleScope.launch {
            viewModel.fetchReview(idMovie).distinctUntilChanged().collectLatest {
                adapter.addLoadStateListener { loadState ->
                    if (loadState.source.refresh is LoadState.NotLoading && adapter.itemCount < 1) {
                        binding?.rvReview?.visibility = View.GONE
                        binding?.layoutNotFound?.visibility = View.VISIBLE
                    } else {
                        binding?.rvReview?.visibility = View.VISIBLE
                        binding?.layoutNotFound?.visibility = View.GONE
                    }
                }
                adapter.submitData(it)
            }
        }
    }
}
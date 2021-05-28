package com.ardhanmz.openmovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.FragmentDetailBinding
import com.ardhanmz.openmovie.ui.adapter.DiscoverAdapter
import com.ardhanmz.openmovie.util.GlideApp
import org.koin.android.ext.android.inject
import java.util.*
import java.util.stream.Collectors


class DetailFragment : Fragment() {
    private val viewModelFactory : DetailViewModelFactory by inject()
    private lateinit var viewModel : DetailViewModel
    private lateinit var binding : FragmentDetailBinding
    val args : DetailFragmentArgs by navArgs()
    private val IMG_BASE_PATH = "http://image.tmdb.org/t/p/original"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        loadData(binding)
        return binding.root
    }

    private fun loadData(binding: FragmentDetailBinding?) {
        val idMovie = args.idMovie
        viewModel.getDetail(idMovie)
        viewModel.detail.observe(viewLifecycleOwner, Observer { detailResponse ->
            binding?.moviePoster?.let {
                GlideApp.with(this)
                    .load(IMG_BASE_PATH + detailResponse.poster_path)
                    .into(it) }
            binding?.movieTitle?.text = detailResponse.title
            binding?.releaseDate?.text = detailResponse.release_date
            binding?.userRating?.text = detailResponse.vote_average.toString()
            binding?.tvDescription?.text = detailResponse.overview
            binding?.tvBudget?.text = detailResponse.budget.toString()
            binding?.tvStatus?.text = detailResponse.status
            if (!detailResponse.genres.isEmpty()) {
                val listGenres: MutableList<String> = ArrayList()
                for (genres in detailResponse.genres) {
                    listGenres.add(genres.name)
                }
                val listString: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    listGenres.stream().map { obj: Any -> obj.toString() }
                        .collect(Collectors.joining(", "))
                } else {
                    listGenres.joinToString(",")
                }
                binding?.tvGenre?.text = listString
            }
        })
        binding?.btnComment?.setOnClickListener{ it ->
            val action = DetailFragmentDirections.actionDetailFragmentToReviewFragment(idMovie)
            it.findNavController().navigate(action)
        }
        binding?.btnVideos?.setOnClickListener{ it ->
            val action = DetailFragmentDirections.actionDetailFragmentToVideoFragment(idMovie)
            it.findNavController().navigate(action)
        }
    }
}
package com.ardhanmz.openmovie.ui.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.FragmentGenreBinding
import com.ardhanmz.openmovie.ui.adapter.GenreAdapter
import org.koin.android.ext.android.inject

class GenreFragment : Fragment() {
    private val viewModelFactory : GenreViewModelFactory by inject()
    private lateinit var viewModel: GenreViewModel
    private lateinit var binding: FragmentGenreBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_genre, container, false)
        viewModel = ViewModelProvider(this,viewModelFactory).get(GenreViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        loadData(binding)
        return binding.root
    }

    private fun loadData(binding: FragmentGenreBinding?) {
        val linearLayoutManager = LinearLayoutManager(
            this.context, RecyclerView.VERTICAL,false)
        binding?.rvGenre?.layoutManager = linearLayoutManager
        viewModel.getListGenre()
        viewModel.genreList.observe(viewLifecycleOwner, Observer { listGenre ->
            binding?.rvGenre?.adapter = GenreAdapter(listGenre)
        })
    }
}
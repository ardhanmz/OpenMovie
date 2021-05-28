package com.ardhanmz.openmovie.ui.discovergenre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.FragmentDiscoverGenreBinding
import com.ardhanmz.openmovie.ui.adapter.DiscoverAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DiscoverGenreFragment() : Fragment() {
    private val viewModelFactory : DiscoverGenreViewModelFactory by inject()
    private lateinit var viewModel : DiscoverGenreViewModel
    private lateinit var binding : FragmentDiscoverGenreBinding
    lateinit var adapter: DiscoverAdapter
    val args : DiscoverGenreFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover_genre, container, false)
        viewModel = ViewModelProvider(this,viewModelFactory).get(DiscoverGenreViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        loadData(binding)
        return binding.root
    }

    private fun loadData(binding: FragmentDiscoverGenreBinding?) {
        val linearLayoutManager = LinearLayoutManager(
            this.context, RecyclerView.VERTICAL,false)
        binding?.rvDiscover?.layoutManager = linearLayoutManager
        adapter = DiscoverAdapter()
        binding?.rvDiscover?.adapter = adapter
        val idGenre = args.idGenre
        lifecycleScope.launch {
            viewModel.fetchDiscovery(idGenre).distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}


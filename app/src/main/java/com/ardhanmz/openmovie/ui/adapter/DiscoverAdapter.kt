package com.ardhanmz.openmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.DiscoverItemsBinding
import com.ardhanmz.openmovie.models.Result
import com.ardhanmz.openmovie.ui.discovergenre.DiscoverGenreFragmentDirections
import com.ardhanmz.openmovie.util.GlideApp

class DiscoverAdapter : PagingDataAdapter<Result, RecyclerView.ViewHolder>(REPO_COMPARATOR){

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
        private const val IMG_BASE_PATH = "http://image.tmdb.org/t/p/original"
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val resultItem = getItem(position)
        if (null != resultItem) {
            (holder as ViewHolder).bind(resultItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.create(parent)
    }

    class ViewHolder(val binding: DiscoverItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        private var result: Result? = null

        fun bind (result: Result?) {
            if (result != null) {
                binding.movieTitle.text = result.title
                binding.movieDesc.text = result.overview
                binding.movieYear.text = result.release_date
                GlideApp.with(itemView.context)
                    .load(IMG_BASE_PATH + result.poster_path)
                    .placeholder(R.drawable.placeholder_img_load)
                    .fitCenter()
                    .into(binding.moviePoster)
                itemView.setOnClickListener{ click ->
//                    Toast.makeText(itemView.context, result.id.toString(),
//                        Toast.LENGTH_SHORT).show()
                    val action = DiscoverGenreFragmentDirections.actionDiscoverGenreFragmentToDetailFragment2(result.id)
                    itemView.findNavController().navigate(action)
                }
            }
        }
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.discover_items, parent, false)
                val binding = DiscoverItemsBinding.bind(view)
                return ViewHolder(binding)
            }
        }
    }
}
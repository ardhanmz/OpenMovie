package com.ardhanmz.openmovie.ui.adapter

import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.ReviewItemsBinding
import com.ardhanmz.openmovie.models.ResultReview
import com.ardhanmz.openmovie.util.GlideApp

class ReviewAdapter:  PagingDataAdapter<ResultReview, RecyclerView.ViewHolder>(REPO_COMPARATOR){
    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ResultReview>() {
            override fun areItemsTheSame(oldItem: ResultReview, newItem: ResultReview): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: ResultReview, newItem: ResultReview): Boolean =
                oldItem == newItem
        }
        private const val IMG_BASE_PATH = "http://image.tmdb.org/t/p/original"
    }
    class ViewHolder(val binding: ReviewItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        private var result: ResultReview? = null

        fun bind (result: ResultReview?) {
            lateinit var imageUrl : String
            if (result != null) {

                binding.tvUsername.text = result.author_details.username
                binding.tvReviewDate.text = result.created_at
                binding.tvReview.text = result.content

                if (null != result.author_details.avatar_path) {
                    if (result.author_details.avatar_path.contains("gravatar")) {
                        imageUrl = result.author_details.avatar_path.substring(1,result.author_details.avatar_path.length)
                    } else {
                        imageUrl = IMG_BASE_PATH + result.author_details.avatar_path
                    }
                    GlideApp.with(itemView.context)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_img_load)
                        .fitCenter()
                        .into(binding.avatarImg)
                }
                itemView.setOnClickListener{ click ->
//                    Toast.makeText(itemView.context, result.id.toString(),
//                        Toast.LENGTH_SHORT).show()
//                    val action = DiscoverGenreFragmentDirections.actionDiscoverGenreFragmentToDetailFragment2(result.id)
//                    itemView.findNavController().navigate(action)
                }
            }
        }
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.review_items, parent, false)
                val binding = ReviewItemsBinding.bind(view)
                return ViewHolder(binding)
            }
        }
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
}
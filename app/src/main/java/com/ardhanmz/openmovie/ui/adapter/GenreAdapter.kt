package com.ardhanmz.openmovie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.R
import com.ardhanmz.openmovie.databinding.GenreItemsBinding
import com.ardhanmz.openmovie.models.GenreResponse
import com.ardhanmz.openmovie.ui.genre.GenreFragmentDirections


class GenreAdapter(
    val genreResponse: GenreResponse
): RecyclerView.Adapter<GenreAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenreItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(genreResponse.genres[position]){
                binding.textviewGenre.text = name
                holder.itemView.setOnClickListener {
                    val action = GenreFragmentDirections.actionGenreFragmentToDiscoverGenreFragment(id)
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return genreResponse.genres.size
    }
    inner class ViewHolder(val binding: GenreItemsBinding) :
            RecyclerView.ViewHolder(binding.root)
}
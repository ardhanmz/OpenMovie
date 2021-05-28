package com.ardhanmz.openmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.ardhanmz.openmovie.databinding.VideoItemsBinding
import com.ardhanmz.openmovie.models.VideosResponse
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class VideoAdapter(
    val result : VideosResponse
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: VideoItemsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        val binding = VideoItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        with(holder){
            with (result.results[position]) {
                binding.videoTitle.text = name
                binding.videoSubtitle.text = type
                binding.youtubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                        val videoId = key
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return result.results.size
    }
}
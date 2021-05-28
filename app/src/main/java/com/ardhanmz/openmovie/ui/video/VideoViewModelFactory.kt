package com.ardhanmz.openmovie.ui.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardhanmz.openmovie.data.repositories.DetailRepository

class VideoViewModelFactory(
    val detailRepository: DetailRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(detailRepository) as T
    }
}
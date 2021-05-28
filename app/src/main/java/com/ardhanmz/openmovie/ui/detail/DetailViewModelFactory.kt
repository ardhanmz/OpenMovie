package com.ardhanmz.openmovie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardhanmz.openmovie.data.repositories.DetailRepository

class DetailViewModelFactory(
    val detailRepository : DetailRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(detailRepository) as T
    }

}

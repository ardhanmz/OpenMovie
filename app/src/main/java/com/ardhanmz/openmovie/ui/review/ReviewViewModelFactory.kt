package com.ardhanmz.openmovie.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardhanmz.openmovie.data.repositories.ReviewRepository
import com.ardhanmz.openmovie.ui.genre.GenreViewModel

class ReviewViewModelFactory(val reviewRepository: ReviewRepository):
ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReviewViewModel(reviewRepository) as T
    }
}
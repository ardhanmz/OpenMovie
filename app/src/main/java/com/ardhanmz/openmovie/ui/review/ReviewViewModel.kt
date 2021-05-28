package com.ardhanmz.openmovie.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ardhanmz.openmovie.data.repositories.ReviewRepository
import com.ardhanmz.openmovie.models.Result
import com.ardhanmz.openmovie.models.ResultReview
import kotlinx.coroutines.flow.Flow

class ReviewViewModel(
    val reviewRepository: ReviewRepository
): ViewModel() {
    fun fetchReview(id: Int): Flow<PagingData<ResultReview>> {
        return reviewRepository.getListReview(id)
            .cachedIn(viewModelScope)
    }
}
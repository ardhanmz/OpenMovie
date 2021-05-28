package com.ardhanmz.openmovie.data.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ardhanmz.openmovie.data.services.DiscoverPagingSource
import com.ardhanmz.openmovie.data.services.ReviewPagingSource
import com.ardhanmz.openmovie.data.services.ReviewService
import com.ardhanmz.openmovie.models.Result
import com.ardhanmz.openmovie.models.ResultReview
import kotlinx.coroutines.flow.Flow

class ReviewRepository(
    val reviewService: ReviewService
) {
    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
    fun getListReview(idMovies: Int): Flow<PagingData<ResultReview>> {
        Log.d("DiscoverRepository", "New page")
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { ReviewPagingSource(reviewService, idMovies) }
        ).flow
    }
}
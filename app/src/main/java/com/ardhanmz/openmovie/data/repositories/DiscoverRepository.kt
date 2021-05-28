package com.ardhanmz.openmovie.data.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ardhanmz.openmovie.data.services.DiscoverPagingSource
import com.ardhanmz.openmovie.data.services.DiscoverService
import com.ardhanmz.openmovie.models.Result
import kotlinx.coroutines.flow.Flow

class DiscoverRepository(
    val discoverService: DiscoverService
) {
    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
//    suspend fun getListMovieByGenre(id : Int) = discoverService.getListMovieByGenre(id)

    fun getListDiscover(id: Int): Flow<PagingData<Result>> {
        Log.d("DiscoverRepository", "New page")
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { DiscoverPagingSource(discoverService, id) }
        ).flow
    }
}
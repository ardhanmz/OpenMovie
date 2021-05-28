package com.ardhanmz.openmovie.data.services

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ardhanmz.openmovie.models.Result
import retrofit2.HttpException
import java.io.IOException

class DiscoverPagingSource(
    val discoverService: DiscoverService,
    id: Int
): PagingSource<Int, Result>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
    val idgenre = id
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = discoverService.getListMovieByGenre(idgenre, position)
            val result = response.results
            LoadResult.Page(
                data = result,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position,
                nextKey = if (result.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }


}
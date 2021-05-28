package com.ardhanmz.openmovie.data.services

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ardhanmz.openmovie.models.ResultReview
import retrofit2.HttpException
import java.io.IOException

class ReviewPagingSource(
    val reviewService: ReviewService,
    val idMovies : Int
) : PagingSource<Int, ResultReview>(){
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ResultReview>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultReview> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = reviewService.getReview(idMovies, position)
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

}
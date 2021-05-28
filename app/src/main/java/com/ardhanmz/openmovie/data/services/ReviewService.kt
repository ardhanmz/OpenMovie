package com.ardhanmz.openmovie.data.services

import com.ardhanmz.openmovie.models.DetailResponse
import com.ardhanmz.openmovie.models.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {
    @GET("movie/{idMovies}/reviews?")
    suspend fun getReview(
        @Path("idMovies") idMovies : Int,
        @Query("page") page: Int,
    ) : ReviewResponse
}
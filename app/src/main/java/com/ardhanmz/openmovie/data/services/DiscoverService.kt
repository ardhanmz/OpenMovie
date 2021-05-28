package com.ardhanmz.openmovie.data.services

import com.ardhanmz.openmovie.models.DiscoverGenres
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {
    @GET("discover/movie?")
    suspend fun getListMovieByGenre(
        @Query("with_genres") id: Int,
        @Query("page") page: Int,
    ) : DiscoverGenres
}
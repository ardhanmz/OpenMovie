package com.ardhanmz.openmovie.data.services

import com.ardhanmz.openmovie.models.DetailResponse
import com.ardhanmz.openmovie.models.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("movie/{idMovies}")
    suspend fun getListMovieByGenre(
        @Path("idMovies") idMovies : Int
    ) : DetailResponse

    @GET("movie/{idMovies}/videos")
    suspend fun getVideos(
        @Path("idMovies") idMovies : Int
    ) : VideosResponse
}
package com.ardhanmz.openmovie.data.services

import com.ardhanmz.openmovie.models.GenreResponse
import retrofit2.Call
import retrofit2.http.GET

interface GenreServices {
    @GET("genre/movie/list")
    suspend fun listGenreMovie(): GenreResponse

    @GET("genre/tv/list")
    suspend fun listGenreTV(): GenreResponse
}
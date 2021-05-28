package com.ardhanmz.openmovie.data.repositories

import com.ardhanmz.openmovie.data.services.DetailService

class DetailRepository(
    val detailService: DetailService
) {
    suspend fun getDetail(idMovies : Int) = detailService.getListMovieByGenre(idMovies)
    suspend fun getVideos(idMovies : Int) = detailService.getVideos(idMovies)
}
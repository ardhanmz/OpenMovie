package com.ardhanmz.openmovie.data.repositories

import com.ardhanmz.openmovie.data.services.GenreServices

class GenreRepository(
    val genreServices: GenreServices
) {
    suspend fun repoGetListGenreMovie() = genreServices.listGenreMovie()
}
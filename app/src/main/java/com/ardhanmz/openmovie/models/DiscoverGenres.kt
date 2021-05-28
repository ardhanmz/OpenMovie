package com.ardhanmz.openmovie.models

data class DiscoverGenres(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
package com.ardhanmz.openmovie.models

data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<ResultReview>,
    val total_pages: Int,
    val total_results: Int
)
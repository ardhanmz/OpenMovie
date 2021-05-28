package com.ardhanmz.openmovie.models

data class VideosResponse(
    val id: Int,
    val results: List<ResultVideo>
)
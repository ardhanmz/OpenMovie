package com.ardhanmz.openmovie.models

import com.google.gson.annotations.SerializedName

data class GenreResponse (
    @SerializedName("genres") var genres : List<Genres>
)
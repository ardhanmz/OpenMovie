package com.ardhanmz.openmovie.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardhanmz.openmovie.data.repositories.GenreRepository

class GenreViewModelFactory(
    val genreRepository: GenreRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GenreViewModel(genreRepository) as T
    }
}
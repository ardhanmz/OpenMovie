package com.ardhanmz.openmovie.ui.discovergenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardhanmz.openmovie.data.repositories.DiscoverRepository
import com.ardhanmz.openmovie.ui.genre.GenreViewModel

@Suppress("UNCHECKED_CAST")
class DiscoverGenreViewModelFactory(
    val discoverRepository: DiscoverRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscoverGenreViewModel(discoverRepository) as T
    }
}
package com.ardhanmz.openmovie.di

import com.ardhanmz.openmovie.ui.detail.DetailViewModelFactory
import com.ardhanmz.openmovie.ui.discovergenre.DiscoverGenreViewModelFactory
import com.ardhanmz.openmovie.ui.genre.GenreViewModel
import com.ardhanmz.openmovie.ui.genre.GenreViewModelFactory
import com.ardhanmz.openmovie.ui.review.ReviewViewModelFactory
import com.ardhanmz.openmovie.ui.video.VideoViewModelFactory
import org.koin.dsl.module

val ViewModelModule = module{
    factory { GenreViewModelFactory(get()) }
    factory { DiscoverGenreViewModelFactory(get()) }
    factory { DetailViewModelFactory(get()) }
    factory { ReviewViewModelFactory(get()) }
    factory { VideoViewModelFactory(get()) }
}
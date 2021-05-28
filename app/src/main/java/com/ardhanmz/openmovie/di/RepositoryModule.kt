package com.ardhanmz.openmovie.di

import com.ardhanmz.openmovie.data.repositories.DetailRepository
import com.ardhanmz.openmovie.data.repositories.DiscoverRepository
import com.ardhanmz.openmovie.data.repositories.GenreRepository
import com.ardhanmz.openmovie.data.repositories.ReviewRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { GenreRepository(get()) }
    single { DiscoverRepository(get()) }
    single { DetailRepository(get())}
    single { ReviewRepository(get())}
}
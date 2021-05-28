package com.ardhanmz.openmovie.ui.discovergenre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ardhanmz.openmovie.data.repositories.DiscoverRepository
import com.ardhanmz.openmovie.models.DiscoverGenres
import com.ardhanmz.openmovie.models.GenreResponse
import com.ardhanmz.openmovie.models.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DiscoverGenreViewModel(
    val discoverRepository: DiscoverRepository
) : ViewModel() {

    /*fun getListMovieByGenre(id : Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = discoverRepository.getListMovieByGenre(id)
                    listMovieByGenre.postValue(result)
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            errorMessage.postValue("Network Error")
                        }
                        is HttpException -> {
                            val codeError = throwable.code()
                            val errorMessageResponse = throwable.message()
                            errorMessage.postValue("Error $errorMessageResponse : $codeError")
                        }
                        else -> {
                            errorMessage.postValue("Uknown error")
                        }
                    }
                }
            }
        }
    }*/
    fun fetchDiscovery(id: Int): Flow<PagingData<Result>> {
        return discoverRepository.getListDiscover(id)
            .cachedIn(viewModelScope)
    }
}
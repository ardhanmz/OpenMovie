package com.ardhanmz.openmovie.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardhanmz.openmovie.models.GenreResponse
import com.ardhanmz.openmovie.data.repositories.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class GenreViewModel(
    val genreRepository: GenreRepository
): ViewModel() {
    val genreList = MutableLiveData<GenreResponse>()
    val errorMessage = MutableLiveData<String>()
    fun getListGenre() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = genreRepository.repoGetListGenreMovie()
                    genreList.postValue(result)
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
    }
}
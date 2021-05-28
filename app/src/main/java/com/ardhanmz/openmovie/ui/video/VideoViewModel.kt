package com.ardhanmz.openmovie.ui.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardhanmz.openmovie.data.repositories.DetailRepository
import com.ardhanmz.openmovie.models.GenreResponse
import com.ardhanmz.openmovie.models.VideosResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class VideoViewModel(
    val detailRepository: DetailRepository
) : ViewModel() {
    val videoList = MutableLiveData<VideosResponse>()
    val errorMessage = MutableLiveData<String>()
    fun getListVideo(idMovies : Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = detailRepository.getVideos(idMovies)
                    videoList.postValue(result)
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
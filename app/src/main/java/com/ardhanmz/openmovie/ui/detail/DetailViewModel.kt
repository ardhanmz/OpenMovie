package com.ardhanmz.openmovie.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardhanmz.openmovie.data.repositories.DetailRepository
import com.ardhanmz.openmovie.models.DetailResponse
import com.ardhanmz.openmovie.models.ResultReview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DetailViewModel( val detailRepository: DetailRepository) : ViewModel() {
    val detail = MutableLiveData<DetailResponse>()
    val errorMessage = MutableLiveData<String>()
    fun getDetail(idMovie : Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = detailRepository.getDetail(idMovie)
                    detail.postValue(result)
                }catch (throwable: Throwable) {
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
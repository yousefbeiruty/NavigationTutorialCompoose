package com.example.navigationtutorial.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.navigationtutorial.model.MovieDetailsResponse
import com.example.navigationtutorial.paging.GenericPagingSource
import com.example.navigationtutorial.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    private val _errorMessage= MutableLiveData<String>()
    val moviesList = Pager(PagingConfig(1)) {
        GenericPagingSource(repository,{error->
            (if(error.isNotEmpty())
                _errorMessage.value=error)
        }) { page ->

            val response = repository.getPopularMoviesList(page)
            response.body()?.results ?: emptyList()
        }
    }.flow.cachedIn(viewModelScope)

    val listTopRated = Pager(PagingConfig(1)) {
        GenericPagingSource(repository,{error->
            (if(error.isNotEmpty())
                _errorMessage.value=error)
        }) { page ->

            val response = repository.getTopRatedMoviesList(page)
            response.body()?.results ?: emptyList()
        }
    }.flow.cachedIn(viewModelScope)

    val revenueList=Pager(PagingConfig(1)) {
        GenericPagingSource(repository,{error->
            (if(error.isNotEmpty())
                _errorMessage.value=error)
        }) { page ->
            val response = repository.getRevenueList(page)
            response.body()?.results ?: emptyList()
        }
    }.flow.cachedIn(viewModelScope)
    //Api
    val detailsMovie = MutableLiveData<MovieDetailsResponse>()

    val errorMessage: LiveData<String> = _errorMessage
    fun loadDetailsMovie(id: Int) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.getMovieDetails(id)
        if (response.isSuccessful) {
            detailsMovie.postValue(response.body())
        }
        loading.postValue(false)
    }

}
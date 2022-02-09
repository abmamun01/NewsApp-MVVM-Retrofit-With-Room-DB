package com.example.newsappmvvmusingkotlin.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvmusingkotlin.Repository.NewsRepository
import com.example.newsappmvvmusingkotlin.Utils.Resource
import com.example.newsappmvvmusingkotlin.ui.Models.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {


    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1


    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) =

        viewModelScope.launch(Dispatchers.IO) {

            breakingNews.postValue(Resource.Loading())
            val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
            //Fragment will automatically notify about that change
            breakingNews.postValue(handleBreakingNewsResponse(response))

            Log.d("THREADCHECHECKING", "getVideosFromViewModel: ${Thread.currentThread().name}")

        }

    fun searchNews(searchQuery: String) =

        viewModelScope.launch {

        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))

    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
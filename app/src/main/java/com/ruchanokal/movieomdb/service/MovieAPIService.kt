package com.ruchanokal.movieomdb.service

import com.ruchanokal.movieomdb.model.Example
import com.ruchanokal.movieomdb.model.MovieModel
import com.ruchanokal.movieomdb.model.Search
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    private val BASE_URL = "https://www.omdbapi.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(MovieAPI::class.java)

    fun getSearchDataService(movieName : String) : Single<Example> {
        return api.getSearchData(movieName)
    }

    fun getMovieDataService(imdbId : String) : Single<MovieModel> {
        return api.getDetailsData(imdbId)
    }
}
package com.ruchanokal.movieomdb.service

import com.ruchanokal.movieomdb.model.Example
import com.ruchanokal.movieomdb.model.MovieModel
import com.ruchanokal.movieomdb.model.Search
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("?&apikey=afc324f1")
    fun getSearchData( @Query("s") movieName : String ) : Single<Example>

    @GET("?&apikey=afc324f1")
    fun getDetailsData( @Query("i") movieName : String ) : Single<MovieModel>
}
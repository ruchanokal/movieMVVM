package com.ruchanokal.movieomdb.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ruchanokal.movieomdb.model.Example
import com.ruchanokal.movieomdb.model.MovieModel
import com.ruchanokal.movieomdb.service.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel : androidx.lifecycle.ViewModel(){

    private val movieAPIService = MovieAPIService()
    private val compositeDisposable  = CompositeDisposable()
    private val TAG = "SearchViewModel"

    val movieSearchData = MutableLiveData<Example>()
    val movieDetailsListData = MutableLiveData<List<MovieModel>>()
    val movieDetailsData = arrayListOf<MovieModel>()

    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()
    val movieNullData = MutableLiveData<Boolean>()

    fun refreshData(movieName : String) {

        movieLoading.value = true

        compositeDisposable.add(movieAPIService
            .getSearchDataService(movieName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver <Example>(){
                override fun onSuccess(t1: Example) {

                    Log.i(TAG,"t1: " + t1)
                    //movieSearchData.value = t1
                    movieDetailsData.clear()

                    var index = 0

                    if (t1.search != null && t1.search.size>0) {

                        for (x in t1.search) {

                            compositeDisposable.add(movieAPIService
                                .getMovieDataService(x.imdbID!!)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(object : DisposableSingleObserver <MovieModel>()  {
                                    override fun onSuccess(t: MovieModel) {
                                        index ++
                                        movieDetailsData.add(t)

                                        if (index == t1.search.size) {
                                            movieDetailsListData.value = movieDetailsData
                                            movieLoading.value = false
                                            movieError.value = false
                                            movieNullData.value = false
                                        }


                                    }

                                    override fun onError(e: Throwable) {

                                        println("error-2: " + e)
                                        movieError.value = true
                                        movieLoading.value = false
                                        movieNullData.value = false

                                        return

                                    }

                                } ))

                        }


                    } else {

                        movieNullData.value = true
                        movieLoading.value = false
                        movieError.value = false

                    }




                }

                override fun onError(e: Throwable) {

                    println("error-1: " + e)
                    movieError.value = true
                    movieLoading.value = false
                    movieNullData.value = false


                }

            }))

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
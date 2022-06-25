package com.ruchanokal.movieomdb.model

import com.google.gson.annotations.SerializedName

data class Example(
    @SerializedName("Search")
    val search : List<Search>,
    @SerializedName("totalResults")
    val totalResults : String,
    @SerializedName("Response")
    val response : String
)


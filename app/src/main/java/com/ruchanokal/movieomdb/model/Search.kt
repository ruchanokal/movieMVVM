package com.ruchanokal.movieomdb.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Search

    (
    @SerializedName("Title")
    var title: String? = null,
    @SerializedName("Year")
    var year: String? = null,
    @SerializedName("imdbID")
    var imdbID: String? = null,
    @SerializedName("Type")
    var type: String? = null,
    @SerializedName("Poster")
    var poster: String? = null )


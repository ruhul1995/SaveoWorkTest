package com.example.saveowork.RetrofitClient

import PopularMoviesPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRequests {
    @GET("/3/movie/popular")
    fun getPopular(@Query("api_key") apikey: String?): Call<PopularMoviesPojo>?

}

package com.example.navigationtutorial.api

import com.example.navigationtutorial.model.MovieDetailsResponse
import com.example.navigationtutorial.model.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("discover/movie")
    suspend fun getPopularMoviesList(@Query("page") page: Int, @Query("sort_by") sortby: String): Response<MoviesListResponse>

    @GET("movie/{movie_id}")
    suspend

    fun getMovieDetails(@Path("movie_id") id: Int): Response<MovieDetailsResponse>

    @GET("discover/movie")
    suspend fun getTopRatedMoviesList(@Query("page") page: Int, @Query("sort_by") sortby: String): Response<MoviesListResponse>

    @GET("discover/movie")
    suspend fun getRevenueList(@Query("page") page: Int, @Query("sort_by") sortby: String): Response<MoviesListResponse>
}
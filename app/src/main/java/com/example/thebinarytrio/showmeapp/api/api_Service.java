package com.example.thebinarytrio.showmeapp.api;

import com.example.thebinarytrio.showmeapp.model.api_MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api_Service
{
    @GET("movie/popular")
    Call<api_MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<api_MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
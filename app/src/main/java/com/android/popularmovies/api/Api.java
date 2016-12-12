package com.android.popularmovies.api;

import com.android.popularmovies.model.Posters;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Giorgio on 03/12/16.
 */

public interface Api {

    @GET("3/movie/popular")
    Observable<Posters> rxPopularMovies(@Query("api_key") String serverKey);

    @GET("3/movie/top_rated")
    Observable<Posters> rxTopRatedMovies(@Query("api_key") String serverKey);
}

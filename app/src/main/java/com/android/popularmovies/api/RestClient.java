package com.android.popularmovies.api;

import com.google.gson.GsonBuilder;

import com.android.popularmovies.BuildConfig;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

public class RestClient {

    private final Retrofit retrofit;

    public RestClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (BuildConfig.LOG_MODE) {
            client.addInterceptor(buildLogInterceptor());
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_END_POINT)
                .client(client.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public Api api() {
        return retrofit.create(Api.class);
    }

    private Interceptor buildLogInterceptor() {
        HttpLoggingInterceptor i = new HttpLoggingInterceptor();
        i.setLevel(HttpLoggingInterceptor.Level.BODY);
        return i;
    }
}

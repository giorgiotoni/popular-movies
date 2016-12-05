package com.android.popularmovies;

import android.app.Application;
import android.content.Context;

import com.android.popularmovies.api.Api;
import com.android.popularmovies.api.RestClient;
import com.android.popularmovies.detail.DetailPresenter;
import com.android.popularmovies.home.HomePresenter;
import com.memoizrlabs.Shank;

/**
 * Created by Giorgio on 27/11/16.
 */

public class PopularMoviesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Shank.registerFactory(Api.class, () -> new RestClient().api());

        Shank.registerFactory(Context.class, this::getApplicationContext);
        Shank.registerFactory(HomePresenter.class, HomePresenter::new);
        Shank.registerFactory(DetailPresenter.class, DetailPresenter::new);
    }
}

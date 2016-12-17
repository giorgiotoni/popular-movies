package com.android.popularmovies;

import android.app.Application;
import android.content.Context;

import com.android.popularmovies.api.Api;
import com.android.popularmovies.api.RestClient;
import com.android.popularmovies.detail.DetailPresenter;
import com.android.popularmovies.home.HomePresenter;
import com.android.popularmovies.services.NetworkService;
import com.android.popularmovies.services.PreferenceService;
import com.memoizrlabs.Shank;

/**
 * Created by Giorgio on 27/11/16.
 */

public class PopularMoviesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Shank.registerFactory(Context.class, () -> this);
        Shank.registerFactory(Api.class, () -> new RestClient().api());

        Shank.registerFactory(NetworkService.class, NetworkService::new);
        Shank.registerFactory(PreferenceService.class, PreferenceService::new);

        Shank.registerFactory(HomePresenter.class, HomePresenter::new);
        Shank.registerFactory(DetailPresenter.class, DetailPresenter::new);
    }
}

package com.android.popularmovies;

import android.app.Application;

import com.android.popularmovies.home.HomePresenter;
import com.memoizrlabs.Shank;

/**
 * Created by Giorgio on 27/11/16.
 */

public class PopularMoviesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Shank.registerFactory(HomePresenter.class, HomePresenter::new);
    }
}

package com.android.popularmovies.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.popularmovies.BuildConfig;
import com.android.popularmovies.api.Api;
import com.android.popularmovies.common.Log;
import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;
import com.android.popularmovies.common.rx.EnhancedObserver;
import com.android.popularmovies.model.Poster;
import com.android.popularmovies.model.Posters;
import com.memoizrlabs.Shank;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Giorgio on 27/11/16.
 */

public class HomePresenter implements Presenter<HomePresenter.View> {

    private Context applicationContext = Shank.provideSingleton(Context.class);
    private Api apiClient = Shank.provideNew(Api.class);
    private View view;
    private Subscription subscription;

    @Override
    public void attach(View view) {
        this.view = view;
        loadPopularMovies();
    }

    @Override
    public void detach() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void destroy() {

    }

    private void loadPopularMovies() {
        if (!isOnline()) {
            view.showNetworkError();
            return;
        }
        subscription = fetchPopularMoviesList().subscribe(new EnhancedObserver<Posters>(subscription) {
            @Override
            public void next(Posters posters) {
                Log.dump(posters); //TODO remove
                if (posters.getPostersList() != null && !posters.getPostersList().isEmpty()) {
                    view.showMoviePosters(posters.getPostersList());
                } else {
                    view.showMoviePostersLoadingError();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMoviePostersLoadingError();
            }
        });
    }

    private Observable<Posters> fetchPopularMoviesList() {
        return apiClient.rxPopularMovies(BuildConfig.SERVER_KEY).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public interface View extends PresenterView {

        void showMoviePosters(List<Poster> posters);

        void showMoviePostersLoadingError();

        void showNetworkError();
    }
}



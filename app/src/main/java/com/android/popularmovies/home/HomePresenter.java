package com.android.popularmovies.home;

import android.content.Context;

import com.android.popularmovies.NetworkService;
import com.android.popularmovies.R;
import com.android.popularmovies.api.Api;
import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;
import com.android.popularmovies.common.rx.EnhancedObserver;
import com.android.popularmovies.model.Poster;
import com.android.popularmovies.model.Posters;
import com.memoizrlabs.Shank;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Giorgio on 27/11/16.
 */

public class HomePresenter implements Presenter<HomePresenter.View> {

    private Context applicationContext = Shank.provideNew(Context.class);
    private NetworkService networkService = Shank.provideSingleton(NetworkService.class);
    private Api apiClient = Shank.provideNew(Api.class);
    private View view;
    private Subscription subscription;

    private List<Poster> mostPopularInCache = new ArrayList<>();
    private List<Poster> topRatedInCache = new ArrayList<>();

    @Override
    public void attach(View view) {
        this.view = view;

        //TODO check which page is displayed
        if (mostPopularInCache.isEmpty()) {
            loadPopularMovies();
        } else {
            view.showMoviePosters(mostPopularInCache);
        }
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
        if (!networkService.isOnline()) {
            view.showNetworkError();
            view.hideRefreshLayout();
            return;
        }
        subscription = fetchPopularMoviesList().subscribe(new EnhancedObserver<Posters>(subscription) {
            @Override
            public void next(Posters posters) {
                if (posters.getPostersList() != null && !posters.getPostersList().isEmpty()) {
                    mostPopularInCache.clear();
                    mostPopularInCache.addAll(posters.getPostersList());
                    view.showMoviePosters(mostPopularInCache);
                } else {
                    view.showMoviePostersLoadingError();
                }
                view.hideRefreshLayout();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMoviePostersLoadingError();
                view.hideRefreshLayout();
            }
        });
    }

    private Observable<Posters> fetchPopularMoviesList() {
        return apiClient.rxPopularMovies(applicationContext.getString(R.string.api_key)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void loadTopRatedMovies() {
        if (!networkService.isOnline()) {
            view.showNetworkError();
            view.hideRefreshLayout();
            return;
        }

        subscription = fetchTopRatedMoviesList().subscribe(new EnhancedObserver<Posters>(subscription) {
            @Override
            public void next(Posters posters) {
                if (posters.getPostersList() != null && !posters.getPostersList().isEmpty()) {
                    topRatedInCache.clear();
                    topRatedInCache.addAll(posters.getPostersList());
                    view.showMoviePosters(topRatedInCache);
                } else {
                    view.showMoviePostersLoadingError();
                }
                view.hideRefreshLayout();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMoviePostersLoadingError();
                view.hideRefreshLayout();
            }
        });
    }

    private Observable<Posters> fetchTopRatedMoviesList() {
        return apiClient.rxTopRatedMovies(applicationContext.getString(R.string.api_key)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    void refreshContent() {
        //TODO check which page is displayed
        loadPopularMovies();
    }

    public interface View extends PresenterView {

        void showMoviePosters(List<Poster> posters);

        void showMoviePostersLoadingError();

        void showNetworkError();

        void hideRefreshLayout();
    }
}



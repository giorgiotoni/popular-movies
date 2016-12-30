package com.android.popularmovies.detail.trailer;

import android.content.Context;

import com.android.popularmovies.R;
import com.android.popularmovies.api.Api;
import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;
import com.android.popularmovies.common.rx.EnhancedObserver;
import com.android.popularmovies.model.Trailer;
import com.android.popularmovies.model.Trailers;
import com.android.popularmovies.services.NetworkService;
import com.memoizrlabs.Shank;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Giorgio on 17/12/16.
 */

public class TrailerPresenter implements Presenter<TrailerPresenter.View> {

    private Context applicationContext = Shank.provideSingleton(Context.class);
    private NetworkService networkService = Shank.provideSingleton(NetworkService.class);
    private Api apiClient = Shank.provideNew(Api.class);
    private View view;
    private Subscription subscription;
    private List<Trailer> trailersInCache = new ArrayList<>();

    @Override
    public void attach(View view) {
        this.view = view;
        if (!trailersInCache.isEmpty()) {
            view.showTrailers(trailersInCache);
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
        trailersInCache.clear();
    }

    public void loadMovieTrailers(String movieId) {
        if (!networkService.isOnline()) {
            if (view != null) {
                view.showNetworkError();
            }
            return;
        }
        subscription = fetchMovieTrailers(movieId).subscribe(new EnhancedObserver<Trailers>(subscription) {
            @Override
            public void next(Trailers trailers) {
                if (trailers.getTrailers() != null && !trailers.getTrailers().isEmpty()) {
                    trailersInCache.addAll(trailers.getTrailers());
                    view.showTrailers(trailersInCache);
                } else {
                    view.showMovieTrailersLoadingError();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMovieTrailersLoadingError();
            }
        });
    }

    private Observable<Trailers> fetchMovieTrailers(String id) {
        return apiClient.rxMovieTrailers(id, applicationContext.getString(R.string.api_key)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public interface View extends PresenterView {

        void showTrailers(List<Trailer> trailers);

        void showNetworkError();

        void showMovieTrailersLoadingError();
    }
}

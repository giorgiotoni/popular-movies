package com.android.popularmovies.detail.review;

import android.content.Context;

import com.android.popularmovies.R;
import com.android.popularmovies.api.Api;
import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;
import com.android.popularmovies.common.rx.EnhancedObserver;
import com.android.popularmovies.model.Review;
import com.android.popularmovies.model.Reviews;
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

public class ReviewPresenter implements Presenter<ReviewPresenter.View> {

    private View view;
    private Context applicationContext = Shank.provideSingleton(Context.class);
    private NetworkService networkService = Shank.provideSingleton(NetworkService.class);
    private Api apiClient = Shank.provideNew(Api.class);
    private Subscription subscription;
    private List<Review> reviewsInCache = new ArrayList<>();

    @Override
    public void attach(View view) {
        this.view = view;
        if (!reviewsInCache.isEmpty()) {
            view.showReviews(reviewsInCache);
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
        reviewsInCache.clear();
    }

    public void loadMovieReviews(String movieId) {
        if (!networkService.isOnline()) {
            if (view != null) {
                view.showNetworkError();
            }
            return;
        }
        subscription = fetchMovieReviews(movieId).subscribe(new EnhancedObserver<Reviews>(subscription) {
            @Override
            public void next(Reviews reviews) {
                if (reviews.getReviews() != null && !reviews.getReviews().isEmpty()) {
                    reviewsInCache.addAll(reviews.getReviews());
                    view.showReviews(reviewsInCache);
                } else {
                    view.showMovieReviewsLoadingError();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMovieReviewsLoadingError();
            }
        });
    }

    private Observable<Reviews> fetchMovieReviews(String id) {
        return apiClient.rxMovieReviews(id, applicationContext.getString(R.string.api_key)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public interface View extends PresenterView {

        void showReviews(List<Review> reviews);

        void showNetworkError();

        void showMovieReviewsLoadingError();
    }
}

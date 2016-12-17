package com.android.popularmovies.detail.review;

import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;
import com.android.popularmovies.model.Review;

import java.util.List;

/**
 * Created by Giorgio on 17/12/16.
 */

public class ReviewPresenter implements Presenter<ReviewPresenter.View> {

    private View view;

    @Override
    public void attach(View view) {
        this.view = view;
    }

    @Override
    public void detach() {

    }

    @Override
    public void destroy() {

    }

    public interface View extends PresenterView {
        void showReviews(List<Review> reviews);
    }
}

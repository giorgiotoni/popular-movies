package com.android.popularmovies.detail.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.popularmovies.R;
import com.android.popularmovies.model.Review;
import com.memoizrlabs.Shank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Giorgio on 17/12/16.
 */

public class ReviewFragment extends Fragment implements ReviewPresenter.View {

    @BindView(R.id.review_recycler)
    RecyclerView recyclerView;

    private ReviewPresenter presenter = Shank.provideSingleton(ReviewPresenter.class);
    private Unbinder unbinder;
    private String movieId;

    public static ReviewFragment getInstance(String movieId) {
        ReviewFragment rf = new ReviewFragment();
        rf.setMovieId(movieId);
        return rf;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        presenter.loadMovieReviews(movieId);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (getActivity().isFinishing()) {
            presenter.destroy();
        }
    }

    @Override
    public void showReviews(List<Review> reviews) {
        ReviewViewAdapter adapter = new ReviewViewAdapter();
        adapter.setItems(reviews);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showNetworkError() {
        //TODO
    }

    @Override
    public void showMovieReviewsLoadingError() {
        //TODO
    }
}

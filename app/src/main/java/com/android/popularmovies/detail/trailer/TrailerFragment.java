package com.android.popularmovies.detail.trailer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.popularmovies.R;
import com.android.popularmovies.model.Trailer;
import com.memoizrlabs.Shank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Giorgio on 17/12/16.
 */

public class TrailerFragment extends Fragment implements TrailerPresenter.View {

    @BindView(R.id.trailer_recycler)
    RecyclerView recyclerView;

    private TrailerPresenter presenter = Shank.provideSingleton(TrailerPresenter.class);
    private Unbinder unbinder;
    private String movieId;

    public static TrailerFragment getInstance(String movieId) {
        TrailerFragment tf = new TrailerFragment();
        tf.setMovieId(movieId);
        return tf;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        presenter.loadMovieTrailers(movieId);
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
        presenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void showTrailers(List<Trailer> trailers) {
        TrailerViewAdapter adapter = new TrailerViewAdapter();
        adapter.setItems(trailers);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showNetworkError() {
        //TODO
    }

    @Override
    public void showMovieTrailersLoadingError() {
        //TODO
    }
}

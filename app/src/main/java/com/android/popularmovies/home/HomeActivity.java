package com.android.popularmovies.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.popularmovies.R;
import com.android.popularmovies.common.Util;
import com.android.popularmovies.common.activity.BaseActivity;
import com.android.popularmovies.model.Poster;
import com.memoizrlabs.Shank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomePresenter.View {

    HomePresenter homePresenter = Shank.provideSingleton(HomePresenter.class);

    @BindView(R.id.home_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, Util.maxNumberOfColumns(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.attach(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        homePresenter.detach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.destroy();
    }

    @Override
    public void showMoviePosters(List<Poster> posters) {
        HomeViewAdapter adapter = new HomeViewAdapter();
        adapter.setItems(posters);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMoviePostersLoadingError() {
        showMessage(R.string.error_loading_posters);
    }

    @Override
    public void showNetworkError() {
        showMessage(R.string.error_network);
    }
}

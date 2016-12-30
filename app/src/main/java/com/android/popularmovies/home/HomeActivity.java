package com.android.popularmovies.home;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.android.popularmovies.R;
import com.android.popularmovies.common.Util;
import com.android.popularmovies.common.activity.BaseActivity;
import com.android.popularmovies.model.Poster;
import com.memoizrlabs.Shank;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomePresenter.View {

    @BindView(R.id.home_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.home_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private HomePresenter homePresenter = Shank.provideSingleton(HomePresenter.class);
    private HomeViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initUI();
        if (savedInstanceState != null && savedInstanceState.containsKey(Poster.class.getSimpleName())) {
            showMoviePosters(savedInstanceState.getParcelableArrayList(Poster.class.getSimpleName()));
        }
    }

    private void initUI() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, Util.maxNumberOfColumns(this)));
        refreshLayout.setOnRefreshListener(() -> homePresenter.refreshContent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.attach(this);
        recyclerView.getLayoutManager().scrollToPosition(homePresenter.getRecyclerScrollPosition());
    }

    @Override
    protected void onPause() {
        super.onPause();
        int currentVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        homePresenter.setRecyclerScrollPosition(currentVisiblePosition);
        homePresenter.detach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_most_popular:
                homePresenter.goToNavView(NavView.MOST_POPULAR);
                return true;
            case R.id.action_top_rated:
                homePresenter.goToNavView(NavView.TOP_RATED);
                return true;
            case R.id.action_favorite:
                homePresenter.goToNavView(NavView.FAVORITE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (homePresenter.getPosterCache() != null) {
            outState.putParcelableArrayList(Poster.class.getSimpleName(), (ArrayList<? extends Parcelable>) homePresenter.getPosterCache());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showMoviePosters(@NonNull List<Poster> posters) {
        if (adapter == null) {
            adapter = new HomeViewAdapter();
        }
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

    @Override
    public void hideRefreshLayout() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoFavoriteMoviesFoundMessage() {
        showMessage(R.string.error_no_favorite_movies);
    }

    @Override
    public void enableRefreshLayout() {
        refreshLayout.setEnabled(true);
    }

    @Override
    public void disableRefreshLayout() {
        refreshLayout.setEnabled(false);
    }
}

package com.android.popularmovies.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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

    @BindView(R.id.home_refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, Util.maxNumberOfColumns(this)));
        refreshLayout.setOnRefreshListener(() -> homePresenter.refreshContent());
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
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void hideRefreshLayout() {
        refreshLayout.setRefreshing(false);
    }
}

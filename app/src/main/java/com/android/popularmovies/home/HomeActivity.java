package com.android.popularmovies.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.popularmovies.R;
import com.android.popularmovies.model.Poster;
import com.memoizrlabs.Shank;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomePresenter.View {

    HomePresenter homePresenter = Shank.provideSingleton(HomePresenter.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
    public void onMoviePostersLoaded(List<Poster> posters) {
        //TODO
    }
}

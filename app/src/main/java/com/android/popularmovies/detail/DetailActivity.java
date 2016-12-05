package com.android.popularmovies.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.popularmovies.common.activity.BaseActivity;
import com.memoizrlabs.Shank;

/**
 * Created by Giorgio on 05/12/16.
 */

public class DetailActivity extends BaseActivity {

    DetailPresenter presenter = Shank.provideSingleton(DetailPresenter.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO
    }
}

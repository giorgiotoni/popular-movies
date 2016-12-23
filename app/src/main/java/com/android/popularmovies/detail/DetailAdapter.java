package com.android.popularmovies.detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.popularmovies.R;
import com.android.popularmovies.common.activity.BaseActivity;
import com.android.popularmovies.detail.review.ReviewFragment;
import com.android.popularmovies.detail.trailer.TrailerFragment;

/**
 * Created by Giorgio on 17/12/16.
 */

public class DetailAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String movieId;

    public DetailAdapter(BaseActivity activity, String movieId) {
        super(activity.getSupportFragmentManager());
        this.context = activity;
        this.movieId = movieId;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return TrailerFragment.getInstance(movieId);
        }
        return ReviewFragment.getInstance(movieId);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.tab_trailers_fragment);
        } else {
            return context.getString(R.string.tab_reviews_fragment);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

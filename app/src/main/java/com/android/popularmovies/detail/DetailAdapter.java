package com.android.popularmovies.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.popularmovies.detail.review.ReviewFragment;
import com.android.popularmovies.detail.trailer.TrailerFragment;

/**
 * Created by Giorgio on 17/12/16.
 */

public class DetailAdapter extends FragmentStatePagerAdapter {

    String movieId;

    public DetailAdapter(FragmentManager fm, String movieId) {
        super(fm);
        this.movieId = movieId;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return TrailerFragment.getInstance(movieId);
        }
        return new ReviewFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}

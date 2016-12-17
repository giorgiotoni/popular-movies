package com.android.popularmovies.detail.review;

import android.view.View;
import android.view.ViewGroup;

import com.android.popularmovies.common.recycler.BaseAdapter;
import com.android.popularmovies.common.recycler.ConcreteViewHolder;
import com.android.popularmovies.model.Review;

/**
 * Created by Giorgio on 17/12/16.
 */

public class ReviewViewAdapter extends BaseAdapter<ConcreteViewHolder<Review>, Review> {

    @Override
    public ConcreteViewHolder<Review> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ReviewItemLayout.inflate(parent);
        return new ConcreteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConcreteViewHolder<Review> holder, int position) {
        holder.update(getItem(position));
    }
}

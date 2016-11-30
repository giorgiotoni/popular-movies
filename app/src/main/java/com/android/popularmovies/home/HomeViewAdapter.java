package com.android.popularmovies.home;

import android.view.View;
import android.view.ViewGroup;

import com.android.popularmovies.common.BaseAdapter;
import com.android.popularmovies.common.ConcreteViewHolder;
import com.android.popularmovies.model.Poster;

/**
 * Created by Giorgio on 30/11/16.
 */

public class HomeViewAdapter extends BaseAdapter<ConcreteViewHolder<Poster>, Poster> {

    private final int itemCount;

    public HomeViewAdapter(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public ConcreteViewHolder<Poster> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = PosterItemLayout.inflate(parent);
        return new ConcreteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConcreteViewHolder<Poster> holder, int position) {
        holder.update(getItem(position));
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}

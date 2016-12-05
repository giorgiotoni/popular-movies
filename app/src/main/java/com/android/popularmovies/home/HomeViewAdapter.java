package com.android.popularmovies.home;

import android.view.View;
import android.view.ViewGroup;

import com.android.popularmovies.common.recycler.BaseAdapter;
import com.android.popularmovies.common.recycler.ConcreteViewHolder;
import com.android.popularmovies.model.Poster;

import java.util.List;

/**
 * Created by Giorgio on 30/11/16.
 */

public class HomeViewAdapter extends BaseAdapter<ConcreteViewHolder<Poster>, Poster> {

    private int itemCount;

    @Override
    public void setItems(List<Poster> items) {
        itemCount = items.size();
        super.setItems(items);
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

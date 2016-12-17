package com.android.popularmovies.detail.trailer;

import android.view.View;
import android.view.ViewGroup;

import com.android.popularmovies.common.recycler.BaseAdapter;
import com.android.popularmovies.common.recycler.ConcreteViewHolder;
import com.android.popularmovies.model.Trailer;

/**
 * Created by Giorgio on 17/12/16.
 */

public class TrailerViewAdapter extends BaseAdapter<ConcreteViewHolder<Trailer>, Trailer> {

    @Override
    public ConcreteViewHolder<Trailer> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = TrailerItemLayout.inflate(parent);
        return new ConcreteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConcreteViewHolder<Trailer> holder, int position) {
        holder.update(getItem(position));
    }
}

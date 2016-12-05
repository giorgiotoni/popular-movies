package com.android.popularmovies.common.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.popularmovies.common.presenter.RecyclerItem;

/**
 * Created by Giorgio on 30/11/16.
 */

public class ConcreteViewHolder<T> extends RecyclerView.ViewHolder {

    public ConcreteViewHolder(View itemView) {
        super(itemView);
    }

    public void update(T item) {
        if (itemView instanceof RecyclerItem) {
            ((RecyclerItem) itemView).update(item);
        }
    }
}

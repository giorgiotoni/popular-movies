package com.android.popularmovies.detail.trailer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.popularmovies.R;
import com.android.popularmovies.common.presenter.RecyclerItem;
import com.android.popularmovies.home.PosterItemLayout;
import com.android.popularmovies.model.Trailer;

import butterknife.ButterKnife;

/**
 * Created by Giorgio on 17/12/16.
 */

public class TrailerItemLayout extends RelativeLayout implements RecyclerItem<Trailer> {

    public TrailerItemLayout(Context context) {
        super(context);
    }

    public TrailerItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrailerItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TrailerItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static PosterItemLayout inflate(ViewGroup viewGroup) {
        return (PosterItemLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_trailer_item, viewGroup, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void update(Trailer trailer) {

    }
}

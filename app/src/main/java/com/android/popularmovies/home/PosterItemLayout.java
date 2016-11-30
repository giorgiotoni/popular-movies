package com.android.popularmovies.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.popularmovies.R;
import com.android.popularmovies.common.RecyclerItem;
import com.android.popularmovies.model.Poster;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Giorgio on 27/11/16.
 */

public class PosterItemLayout extends RelativeLayout implements RecyclerItem<Poster> {

    @BindView(R.id.poster_image)
    ImageView posterImage;

    public PosterItemLayout(Context context) {
        super(context);
    }

    public PosterItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PosterItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PosterItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static PosterItemLayout inflate(ViewGroup viewGroup) {
        return (PosterItemLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_poster_item, viewGroup, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void update(Poster poster) {
        Picasso.with(getContext()).load(poster.getImageUrl()).into(posterImage);
    }
}

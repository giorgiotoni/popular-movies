package com.android.popularmovies.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.popularmovies.BuildConfig;
import com.android.popularmovies.R;
import com.android.popularmovies.common.presenter.RecyclerItem;
import com.android.popularmovies.detail.DetailActivity;
import com.android.popularmovies.model.Poster;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Giorgio on 27/11/16.
 */

public class PosterItemLayout extends FrameLayout implements RecyclerItem<Poster> {

    @BindView(R.id.poster_image)
    ImageView posterImage;

    private Poster poster;

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
        this.poster = poster;
        Picasso.with(getContext()).load(BuildConfig.IMAGES_END_POINT + poster.getImageUrl()).into(posterImage);
    }

    @OnClick(R.id.poster_item_overlay)
    void onPosterClick(){
        DetailActivity.start(getContext(), poster);
    }
}

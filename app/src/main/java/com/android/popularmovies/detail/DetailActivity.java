package com.android.popularmovies.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.popularmovies.BuildConfig;
import com.android.popularmovies.R;
import com.android.popularmovies.common.activity.BaseActivity;
import com.android.popularmovies.model.Poster;
import com.memoizrlabs.Shank;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Giorgio on 05/12/16.
 */

public class DetailActivity extends BaseActivity implements DetailPresenter.View {

    DetailPresenter presenter = Shank.provideSingleton(DetailPresenter.class);
    private Poster poster;

    @BindView(R.id.detail_title)
    TextView title;

    @BindView(R.id.detail_year)
    TextView year;

    @BindView(R.id.detail_duration)
    TextView duration;

    @BindView(R.id.detail_vote)
    TextView vote;

    @BindView(R.id.detail_poster)
    ImageView posterImage;

    @BindView(R.id.detail_description)
    TextView description;

    public static void start(Context context, Poster poster) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Poster.class.getSimpleName(), poster);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        poster = (Poster) getIntent().getSerializableExtra(Poster.class.getSimpleName());
        initUI();
    }

    private void initUI() {
        title.setText(poster.getTitle());
        year.setText(poster.getReleaseDate());
        duration.setText(poster.getReleaseDate());
        vote.setText(getString(R.string.vote_everage, String.valueOf(poster.getVoteEverage())));
        description.setText(poster.getOverview());
        Picasso.with(this).load(BuildConfig.IMAGES_END_POINT + poster.getImageUrl()).into(posterImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @OnClick(R.id.detail_favorite)
    void onAddToFavoriteClick(){
        //TODO
    }
}

package com.android.popularmovies.detail.trailer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.popularmovies.R;
import com.android.popularmovies.common.presenter.RecyclerItem;
import com.android.popularmovies.model.Trailer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Giorgio on 17/12/16.
 */

public class TrailerItemLayout extends RelativeLayout implements RecyclerItem<Trailer> {

    @BindView(R.id.trailer_name)
    TextView name;

    private Trailer trailer;

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

    public static TrailerItemLayout inflate(ViewGroup viewGroup) {
        return (TrailerItemLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_trailer_item, viewGroup, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void update(Trailer trailer) {
        this.trailer = trailer;
        name.setText(trailer.getName());
    }

    @OnClick(R.id.trailer_root_view)
    void onTrailerSelected() {
        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey())));
    }
}

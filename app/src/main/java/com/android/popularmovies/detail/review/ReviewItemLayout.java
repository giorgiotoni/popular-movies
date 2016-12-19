package com.android.popularmovies.detail.review;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.popularmovies.R;
import com.android.popularmovies.common.presenter.RecyclerItem;
import com.android.popularmovies.model.Review;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Giorgio on 17/12/16.
 */

public class ReviewItemLayout extends RelativeLayout implements RecyclerItem<Review> {

    @BindView(R.id.review_text)
    TextView reviewContent;

    private Review review;

    public ReviewItemLayout(Context context) {
        super(context);
    }

    public ReviewItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReviewItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReviewItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static ReviewItemLayout inflate(ViewGroup viewGroup) {
        return (ReviewItemLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_review_item, viewGroup, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void update(Review review) {
        this.review = review;
        reviewContent.setText(review.getContent());
    }
}

package com.android.popularmovies.detail;

import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;

/**
 * Created by Giorgio on 05/12/16.
 */

public class DetailPresenter implements Presenter<DetailPresenter.View> {

    private View view;

    @Override
    public void attach(View view) {
        this.view = view;
    }

    @Override
    public void detach() {

    }

    @Override
    public void destroy() {

    }

    public interface View extends PresenterView {

    }
}

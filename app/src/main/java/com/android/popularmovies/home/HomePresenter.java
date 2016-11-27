package com.android.popularmovies.home;

import com.android.popularmovies.common.Presenter;
import com.android.popularmovies.common.PresenterView;
import com.android.popularmovies.model.Poster;

import java.util.List;

/**
 * Created by Giorgio on 27/11/16.
 */

public class HomePresenter implements Presenter<HomePresenter.View> {

    @Override
    public void attach(View view) {

    }

    @Override
    public void detach() {

    }

    @Override
    public void destroy() {

    }

    public void loadPosters() {
        //TODO
    }

    public interface View extends PresenterView {
        void onMoviePostersLoaded(List<Poster> posters);
    }
}



package com.android.popularmovies.detail;

import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;
import com.android.popularmovies.model.Poster;
import com.android.popularmovies.services.PreferenceService;
import com.memoizrlabs.Shank;

import java.util.List;

/**
 * Created by Giorgio on 05/12/16.
 */

public class DetailPresenter implements Presenter<DetailPresenter.View> {

    PreferenceService preferences = Shank.provideSingleton(PreferenceService.class);

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

    public void addFavoriteMovie(Poster poster) {
        if (!isFavorite(poster)) {
            preferences.save(poster, PreferenceService.FAVORITE_MOVIES);
            view.showMovieAddedToFavorite();
        }
    }

    public boolean isFavorite(Poster poster) {
        List<Poster> posters = preferences.get(Poster.class, PreferenceService.FAVORITE_MOVIES);
        if (posters != null) {
            for (Poster p : posters) {
                if (p.getId() == poster.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public interface View extends PresenterView {

        void showMovieAddedToFavorite();
    }
}

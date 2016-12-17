package com.android.popularmovies.detail;

import com.android.popularmovies.common.presenter.Presenter;
import com.android.popularmovies.common.presenter.PresenterView;
import com.android.popularmovies.model.Poster;
import com.android.popularmovies.model.Posters;
import com.android.popularmovies.services.PreferenceService;
import com.memoizrlabs.Shank;

/**
 * Created by Giorgio on 05/12/16.
 */

public class DetailPresenter implements Presenter<DetailPresenter.View> {

    private PreferenceService preferences = Shank.provideSingleton(PreferenceService.class);

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

    public void addOrRemoveFavoriteMovie(Poster poster) {
        if (isFavorite(poster)) {
            removeFavoriteMovie(poster);
        } else {
            addFavoriteMovie(poster);
        }
    }

    private void addFavoriteMovie(Poster poster) {
        Posters posters = new Posters();
        if (null != preferences.get(Posters.class, PreferenceService.FAVORITE_MOVIES)) {
            posters = preferences.get(Posters.class, PreferenceService.FAVORITE_MOVIES);
        }
        posters.add(poster);
        preferences.save(posters, PreferenceService.FAVORITE_MOVIES);
        view.updateFavoriteButtonStatus();
    }

    private void removeFavoriteMovie(Poster poster) {
        Posters posters = preferences.get(Posters.class, PreferenceService.FAVORITE_MOVIES);
        posters.remove(poster);
        preferences.save(posters, PreferenceService.FAVORITE_MOVIES);
        view.updateFavoriteButtonStatus();
    }

    public boolean isFavorite(Poster poster) {
        Posters posters = preferences.get(Posters.class, PreferenceService.FAVORITE_MOVIES);
        if (posters != null && posters.getPostersList() != null && !posters.getPostersList().isEmpty()) {
            for (Poster p : posters.getPostersList()) {
                if (p.getId().equals(poster.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public interface View extends PresenterView {

        void updateFavoriteButtonStatus();
    }
}

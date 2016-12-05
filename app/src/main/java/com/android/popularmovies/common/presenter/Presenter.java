package com.android.popularmovies.common.presenter;

/**
 * Created by Giorgio on 27/11/16.
 */

public interface Presenter<V extends PresenterView> {

    void attach(V v);

    void detach();

    void destroy();
}


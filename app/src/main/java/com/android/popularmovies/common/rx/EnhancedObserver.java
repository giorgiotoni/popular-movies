package com.android.popularmovies.common.rx;

import rx.Observer;
import rx.Subscription;

public abstract class EnhancedObserver<T> implements Observer<T> {

    private Subscription subscription;

    public EnhancedObserver(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public final void onCompleted() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public final void onNext(T t) {
        try {
            next(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void next(T t);
}

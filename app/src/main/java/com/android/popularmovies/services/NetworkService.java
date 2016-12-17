package com.android.popularmovies.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.memoizrlabs.Shank;

/**
 * Created by Giorgio on 06/12/16.
 */

public class NetworkService {

    Context context;

    public NetworkService() {
        this.context = Shank.provideSingleton(Context.class);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

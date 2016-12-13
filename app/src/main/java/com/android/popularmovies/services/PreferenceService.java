package com.android.popularmovies.services;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.memoizrlabs.Shank;

import java.lang.reflect.Type;

import static android.media.tv.TvContract.Programs.Genres.MOVIES;

/**
 * Created by Giorgio on 13/12/16.
 */

public class PreferenceService {

    private final Context context;

    public PreferenceService() {
        this.context = Shank.provideSingleton(Context.class);
    }

    protected  <T> void save(T t, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putString(MOVIES, new Gson().toJson(t)).apply();
    }

    protected  <T> T get(Type clazz, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String json = pref.getString(MOVIES, null);
        if (json == null) {
            return null;
        }
        return new Gson().fromJson(json, clazz);
    }
}

package com.android.popularmovies.services;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.memoizrlabs.Shank;

import java.lang.reflect.Type;

/**
 * Created by Giorgio on 13/12/16.
 */

public class PreferenceService {

    public static final String FAVORITE_MOVIES = "popular_movies_favourite";
    private final Context context;

    public PreferenceService() {
        this.context = Shank.provideSingleton(Context.class);
    }

    public <T> void save(T t, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.edit().putString(key, new Gson().toJson(t)).apply();
    }

    public <T> T get(Type clazz, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String json = pref.getString(key, null);
        if (json == null) {
            return null;
        }
        return new Gson().fromJson(json, clazz);
    }
}

package com.android.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Giorgio on 03/12/16.
 */

public class Posters {

    @SerializedName("results")
    private List<Poster> posters;

    public List<Poster> getPostersList() {
        return posters;
    }
}

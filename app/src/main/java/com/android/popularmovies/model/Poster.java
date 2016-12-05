package com.android.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Giorgio on 27/11/16.
 */
public class Poster implements Serializable {

    @SerializedName("poster_path")
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}

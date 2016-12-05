package com.android.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Giorgio on 27/11/16.
 */
public class Poster implements Serializable {

    @SerializedName("poster_path")
    private String imageUrl;

    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    private Long id;

    private String title;

    @SerializedName("vote_average")
    private double voteEverage;



    public String getImageUrl() {
        return imageUrl;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteEverage() {
        return voteEverage;
    }
}

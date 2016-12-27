package com.android.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Giorgio on 27/11/16.
 */
public class Poster implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(imageUrl);
        out.writeString(overview);
        out.writeString(releaseDate);
        out.writeString(title);
        out.writeLong(id);
        out.writeDouble(voteEverage);
    }

    public static final Parcelable.Creator<Poster> CREATOR
            = new Parcelable.Creator<Poster>() {
        public Poster createFromParcel(Parcel in) {
            return new Poster(in);
        }

        public Poster[] newArray(int size) {
            return new Poster[size];
        }
    };

    private Poster(Parcel in) {
        imageUrl = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        title = in.readString();
        id = in.readLong();
        voteEverage = in.readDouble();
    }
}

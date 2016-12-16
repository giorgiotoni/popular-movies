package com.android.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

    public void add(Poster poster) {
        if (posters == null) {
            posters = new ArrayList<>();
        }
        posters.add(poster);
    }

    public void remove(Poster poster) {
        for (Poster p : posters){
            if (p.getId().equals(poster.getId())){
                posters.remove(p);
            }
        }
    }
}

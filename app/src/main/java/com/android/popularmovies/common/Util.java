package com.android.popularmovies.common;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Giorgio on 30/11/16.
 */

public class Util {

    public static int maxNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
}

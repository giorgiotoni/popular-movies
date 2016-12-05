package com.android.popularmovies.common.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Giorgio on 03/12/16.
 */

public class BaseActivity extends AppCompatActivity {

    public void showMessage(String error) {
        if (getCurrentFocus() != null) {
            Snackbar.make(getCurrentFocus(), error, Snackbar.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void showMessage(int resourceString) {
        showMessage(getString(resourceString));
    }
}

package com.android.popularmovies.common.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.popularmovies.R;

/**
 * Created by Giorgio on 03/12/16.
 */

public class BaseActivity extends AppCompatActivity {

    @Nullable
    private Toolbar toolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setupToolbar();
    }

    protected void setupToolbar() {
        View v = getView().findViewById(R.id.toolbar);
        if (v != null && v instanceof Toolbar) {
            toolbar = (Toolbar) v;
        } else {
            return;
        }
        setSupportActionBar(toolbar);
    }

    public View getView() {
        if (getCurrentFocus() == null) {
            return getWindow().getDecorView().findViewById(android.R.id.content);
        }
        return getCurrentFocus();
    }

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

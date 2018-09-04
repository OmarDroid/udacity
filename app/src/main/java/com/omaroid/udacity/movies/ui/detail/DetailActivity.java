package com.omaroid.udacity.movies.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.ui.main.MainActivityFragment;

import static com.omaroid.udacity.movies.utils.Constants.MOVIE_OBJECT;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content_main_fragment, new DetailActivityFragment()).commit();
        }
    }

}

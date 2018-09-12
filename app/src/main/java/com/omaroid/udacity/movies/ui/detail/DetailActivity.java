package com.omaroid.udacity.movies.ui.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.omaroid.udacity.movies.utils.Constants.MOVIE_OBJECT;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Bundle movieBundle = getIntent().getExtras();
        if (null != movieBundle) {
            movie = movieBundle.getParcelable(MOVIE_OBJECT);
            toolbar.setTitle(movie.getTitle());
        }
        if (savedInstanceState == null) {
            Fragment fragment = DetailActivityFragment.newInstance(movie);
            getSupportFragmentManager().beginTransaction().add(R.id.content_detail_fragment, fragment).commit();
        }
    }
}

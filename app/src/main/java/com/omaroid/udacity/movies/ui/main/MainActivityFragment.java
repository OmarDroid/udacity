package com.omaroid.udacity.movies.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.adapter.MovieAdapter;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.presenter.main.MainActivityContract;
import com.omaroid.udacity.movies.presenter.main.MainActivityPresenter;
import com.omaroid.udacity.movies.ui.detail.DetailActivity;

import java.util.ArrayList;

import static com.omaroid.udacity.movies.utils.Constants.MOVIE_OBJECT;
import static com.omaroid.udacity.movies.utils.Constants.POPULARITY_SORT;
import static com.omaroid.udacity.movies.utils.Constants.VOTE_AVERAGE_SORT;

public class MainActivityFragment extends Fragment implements MainActivityContract.View, AdapterView.OnItemClickListener {

    private MainActivityContract.Presenter presenter;
    private ProgressBar mProgressBar;
    private GridView mGridView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new MainActivityPresenter(this);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mGridView = view.findViewById(R.id.gridView);
        mGridView.setOnItemClickListener(this);
        mProgressBar = view.findViewById(R.id.progressBar);
        movieAdapter = new MovieAdapter(getContext());
        if (null == savedInstanceState) {
            movies = new ArrayList<>();
            presenter.getMoviesList(POPULARITY_SORT, movieAdapter);
        } else {
            ArrayList<Movie> tempMoviesList = savedInstanceState.getParcelableArrayList("moviesList");
            movieAdapter.setMoviesList(tempMoviesList);
        }
        mGridView.setAdapter(movieAdapter);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        movies = movieAdapter.getMoviesList();
        outState.putParcelableArrayList("moviesList", movies);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        switch (itemSelected) {
            case R.id.action_sortPop:
                getMovies(POPULARITY_SORT);
                break;
            case R.id.action_sortTop:
                getMovies(VOTE_AVERAGE_SORT);
                break;
            default:
                getMovies(POPULARITY_SORT);
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMovies(String sort) {
        presenter.getMoviesList(sort, movieAdapter);
    }

    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeToast(int message) {
        Snackbar snackbar = Snackbar
                .make(getView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showProgressIndicator(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void setMovies(MovieAdapter movieAdapter) {
        mGridView.setAdapter(movieAdapter);
    }

    @Override
    public void switchToItemDetailActivity() {
        Intent detailViewIntent = new Intent(getActivity(), DetailActivity.class);
        startActivity(detailViewIntent);
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent detailViewIntent = new Intent(getActivity(), DetailActivity.class);
        detailViewIntent.putExtra(MOVIE_OBJECT, movieAdapter.getItem(i));
        startActivity(detailViewIntent);
    }

}

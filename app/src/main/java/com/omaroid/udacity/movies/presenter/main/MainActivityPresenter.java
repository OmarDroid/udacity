package com.omaroid.udacity.movies.presenter.main;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.adapter.MoviesAdapter;
import com.omaroid.udacity.movies.model.MovieList;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.network.GetMoviesDataService;
import com.omaroid.udacity.movies.network.RetrofitInstance;
import com.omaroid.udacity.movies.utils.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MoviesAdapter movieAdapter;
    private String order;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getMoviesList(String order, MoviesAdapter movieAdapter) {
        this.movieAdapter = movieAdapter;
        this.order = order;
        movies.clear();
        view.showProgressIndicator(true);
        getMoviesListDataService();
    }

    private void getMoviesListDataService() {
        GetMoviesDataService getMoviesDataService = RetrofitInstance.getRetrofitInstance().create(GetMoviesDataService.class);
        final Call<MovieList> movieCall = getMoviesDataService.getTopMovies(Constants.API_KEY, Constants.LANGUAGE_US, order, Constants.VOTE_COUNT);
        movieCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                view.showProgressIndicator(false);
                movies = response.body().getResults();
                movieAdapter.setMoviesList(movies);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                view.showProgressIndicator(false);
                view.makeToast(R.string.check_connection);
            }
        });
    }

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }


    @Override
    public void onItemClicked(Movie movie) {
        view.switchToItemDetailActivity(movie);
    }
}

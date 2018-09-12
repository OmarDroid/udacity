package com.omaroid.udacity.movies.presenter.main;

import com.omaroid.udacity.movies.adapter.MoviesAdapter;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.utils.BaseView;

public interface MainActivityContract {
    interface View extends BaseView<Presenter> {
        void setPresenter(MainActivityContract.Presenter presenter);

        void makeToast(int message);

        void showProgressIndicator(boolean show);

        void setMovies(MoviesAdapter movieAdapter);

        void switchToItemDetailActivity(Movie movie);
    }

    interface Presenter {
        void getMoviesList(String order, MoviesAdapter movieAdapter);

        void onItemClicked(Movie movie);

        void attachView(View view);

        void detachView();
    }
}

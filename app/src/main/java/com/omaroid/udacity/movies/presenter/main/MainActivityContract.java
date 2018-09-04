package com.omaroid.udacity.movies.presenter.main;

import com.omaroid.udacity.movies.adapter.MovieAdapter;
import com.omaroid.udacity.movies.utils.BaseView;

public interface MainActivityContract {
    interface View extends BaseView<Presenter> {
        void setPresenter(MainActivityContract.Presenter presenter);

        void makeToast(int message);

        void showProgressIndicator(boolean show);

        void setMovies(MovieAdapter movieAdapter);

        void switchToItemDetailActivity();
    }

    interface Presenter {
        void getMoviesList(String order, MovieAdapter movieAdapter);

        void onItemClicked();

        void attachView(View view);

        void detachView();
    }
}

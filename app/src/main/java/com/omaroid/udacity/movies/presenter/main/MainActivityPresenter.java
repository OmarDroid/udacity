package com.omaroid.udacity.movies.presenter.main;

import android.net.Uri;
import android.util.Log;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.adapter.MovieAdapter;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private String order;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getMoviesList(String order, MovieAdapter movieAdapter) {
        this.movieAdapter = movieAdapter;
        this.order = order;
        movies.clear();
        new FetchMoviesAsyncTask().execute();
    }

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public class FetchMoviesAsyncTask extends android.os.AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showProgressIndicator(true);
        }

        private final String LOG_TAG = FetchMoviesAsyncTask.class.getSimpleName();

        private ArrayList<Movie> getMoviesDataFromJson(String moviesJsonStr)
                throws JSONException {

            JSONObject moviesJson = new JSONObject(moviesJsonStr);
            JSONArray moviesArray = moviesJson.getJSONArray(Constants.TMDB_RESULTS);

            for (int i = 0; i < moviesArray.length(); i++) {

                JSONObject singleMovieObject = moviesArray.getJSONObject(i);

                String originalTitle = singleMovieObject.optString(Constants.TMDB_TITLE);
                String userRating = singleMovieObject.optString(Constants.TMDB_VOTE_AVG);
                String releaseDate = singleMovieObject.optString(Constants.TMDB_RELEASE_DATE);
                String overview = singleMovieObject.optString(Constants.TMDB_OVERVIEW);
                String posterPath = singleMovieObject.optString(Constants.TMDB_POSTER);

                movies.add(new Movie(originalTitle, userRating, releaseDate, overview, posterPath));

            }
            return movies;
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String moviesJsonStr;

            try {
                Uri builtUri = Uri.parse(Constants.MOVIES_BASE_URL).buildUpon()
                        .appendQueryParameter(Constants.API_KEY_PARAM, Constants.API_KEY)
                        .appendQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_US)
                        .appendQueryParameter(Constants.SORT_PARAM, order)
                        .appendQueryParameter(Constants.VOTE_COUNT_PARAM, Constants.VOTE_COUNT)
                        .build();
                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                moviesJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error", e);
                    }
                }
            }

            try {
                return getMoviesDataFromJson(moviesJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            view.showProgressIndicator(false);
            if (movies != null && movies.size() > 0) {
                movieAdapter.clear();
                for (Movie moviesStr : movies) {
                    movieAdapter.add(moviesStr);
                }
                movieAdapter.setMoviesList(movies);
                view.setMovies(movieAdapter);
            } else if (movies == null) {
                view.makeToast(R.string.check_connection);
            } else {
                view.makeToast(R.string.not_available);
            }

        }
    }

    @Override
    public void onItemClicked() {
        view.switchToItemDetailActivity();
    }
}

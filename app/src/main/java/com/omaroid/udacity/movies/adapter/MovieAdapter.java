package com.omaroid.udacity.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private ArrayList<Movie> moviesList = new ArrayList<>();

    public MovieAdapter(Context context) {
        super(context, 0);
        this.context = context;

    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Movie getItem(int i) {
        return moviesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView moviePoster = view.findViewById(R.id.grid_item_imgView);
        Movie movies = moviesList.get(i);
        String imgPosterPath = movies.getPosterPath();

        String PosterUrl = Constants.IMAGE_BASE_URL + Constants.POSTER_SIZE_LARGE + imgPosterPath;
        Picasso.get().load(PosterUrl).into(moviePoster);

        return view;
    }

    public void add(Movie movies) {
        moviesList.add(movies);
    }

    public void clear() {
        moviesList.clear();
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(ArrayList<Movie> mMoviesList) {
        if (moviesList.size() == 0) {
            moviesList = mMoviesList;
        }
        notifyDataSetChanged();
    }
}

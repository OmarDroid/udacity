package com.omaroid.udacity.movies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.presenter.main.MainActivityContract;
import com.omaroid.udacity.movies.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movie> moviesList;
    private MainActivityContract.Presenter mainActivityPresenter;

    public MoviesAdapter(MainActivityContract.Presenter mainActivityPresenter, ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mainActivityPresenter = mainActivityPresenter;
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item_view, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {

        String PosterUrl = Constants.IMAGE_BASE_URL + Constants.POSTER_SIZE_LARGE + moviesList.get(position).getPosterPath();
        Picasso.get().load(PosterUrl).into(holder.ivMoviePoster);

        holder.tvMovieTitle.setText(moviesList.get(position).getOriginalTitle());
        holder.tvReleaseDate.setText(moviesList.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        if (null != moviesList) {
            return moviesList.size();
        }
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        ImageView ivMoviePoster;
        TextView tvMovieTitle;
        TextView tvReleaseDate;

        MovieViewHolder(final View itemView) {
            super(itemView);
            ivMoviePoster = itemView.findViewById(R.id.iv_movie_detail_poster);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_detail_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_movie_detail_release_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Movie movie = moviesList.get(this.getPosition());
            mainActivityPresenter.onItemClicked(movie);
        }
    }

    public void setMoviesList(ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }
}

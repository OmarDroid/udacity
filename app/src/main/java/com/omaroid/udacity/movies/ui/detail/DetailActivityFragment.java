package com.omaroid.udacity.movies.ui.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static com.omaroid.udacity.movies.utils.Constants.MOVIE_OBJECT;

public class DetailActivityFragment extends Fragment {


    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Movie movie = getActivity().getIntent().getExtras().getParcelable(MOVIE_OBJECT);

        ImageView moviePosterImageView =  view.findViewById(R.id.iv_movie_detail_poster);
        final ProgressBar moviePosterProgressBar = view.findViewById(R.id.pb_movie_detail_poster);
        TextView movieTitleTextView =  view.findViewById(R.id.tv_movie_detail_title);
        TextView movieVoteAverageTextView = view.findViewById(R.id.tv_movie_detail_vote_average);
        TextView movieReleaseDateTextView =  view.findViewById(R.id.tv_movie_detail_release_date);
        TextView movieOverviewTextView = view.findViewById(R.id.tv_movie_detail_overview);
        final TextView moviePosterErrorTextView = view.findViewById(R.id.tv_movie_detail_poster_error);

        Picasso.get().load(movie.buildPosterPath(getContext())).into(moviePosterImageView, new Callback() {
            @Override
            public void onSuccess() {
                moviePosterProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                moviePosterProgressBar.setVisibility(View.GONE);
                moviePosterErrorTextView.setRotation(-20);
                moviePosterErrorTextView.setVisibility(View.VISIBLE);
            }
        });
        movieTitleTextView.append((getString(R.string.movie_title)));
        movieTitleTextView.append(movie.getOriginalTitle());
        movieVoteAverageTextView.append((getString(R.string.vote_average)));
        movieVoteAverageTextView.append(movie.getUserRating()+"\n");
        movieReleaseDateTextView.append((getString(R.string.release_date)));
        movieReleaseDateTextView.append(movie.getReleaseDate()+"\n");
        movieOverviewTextView.append((getString(R.string.overview)));
        movieOverviewTextView.append(movie.getOverView());
        return view;
    }
}

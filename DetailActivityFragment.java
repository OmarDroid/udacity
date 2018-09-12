package com.omaroid.udacity.movies.ui.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.omaroid.udacity.movies.R;
import com.omaroid.udacity.movies.model.Movie;
import com.omaroid.udacity.movies.utils.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.omaroid.udacity.movies.utils.Constants.MOVIE_OBJECT;

public class DetailActivityFragment extends Fragment {

    @BindView(R.id.pb_movie_detail_poster)
    ProgressBar moviePosterProgressBar;

    @BindView(R.id.tv_movie_error)
    TextView movieErrorTextView;

    @BindView(R.id.tv_movie_detail_vote_average)
    TextView movieVoteAverageTextView;

    @BindView(R.id.tv_movie_detail_release_date)
    TextView movieReleaseDateTextView;

    @BindView(R.id.tv_movie_detail_overview)
    TextView movieOverviewTextView;

    @BindView(R.id.iv_movie_detail_poster)
    ImageView moviePosterImageView;

    private Movie movie;

    public DetailActivityFragment() {
    }

    public static DetailActivityFragment newInstance(Movie movieParam) {
        DetailActivityFragment fragment = new DetailActivityFragment();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE_OBJECT, movieParam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments())
            movie = getArguments().getParcelable(MOVIE_OBJECT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        if (null != movie) {
            initMovie();
        } else {
            showErrorLayout();
        }
        return view;
    }


    private void initMovie() {

        Picasso.get().load(Constants.IMAGE_BASE_URL + Constants.POSTER_SIZE_XLARGE + movie.getPosterPath()).into(moviePosterImageView, new Callback() {
            @Override
            public void onSuccess() {
                moviePosterProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                moviePosterProgressBar.setVisibility(View.GONE);
                setErrorPosterLayout();
            }
        });

        movieVoteAverageTextView.append((getString(R.string.vote_average)));
        movieVoteAverageTextView.append(" " + movie.getVoteAverage() + "\n");
        movieReleaseDateTextView.append((getString(R.string.release_date)));
        movieReleaseDateTextView.append(" " + movie.getReleaseDate() + "\n");
        movieOverviewTextView.append((getString(R.string.overview)));
        movieOverviewTextView.append(" " + movie.getOverview());
    }

    private void showErrorLayout() {
        movieErrorTextView.setVisibility(View.VISIBLE);
        movieErrorTextView.setText(getString(R.string.not_available));
        movieOverviewTextView.setVisibility(View.GONE);
        movieVoteAverageTextView.setVisibility(View.GONE);
        movieReleaseDateTextView.setVisibility(View.GONE);
        setErrorPosterLayout();
    }

    private void setErrorPosterLayout() {
        moviePosterImageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.oops));
        moviePosterImageView.getLayoutParams().height = 150;
        moviePosterImageView.getLayoutParams().width = 150;
        moviePosterImageView.requestLayout();

    }
}

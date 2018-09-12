package com.omaroid.udacity.movies.network;

import com.omaroid.udacity.movies.model.MovieList;
import com.omaroid.udacity.movies.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMoviesDataService {
    @GET("movie/")
    Call<MovieList> getTopMovies(@Query(Constants.API_KEY_PARAM) String apiKey,
                                 @Query(Constants.LANGUAGE_PARAM) String language,
                                 @Query(Constants.SORT_PARAM) String sortBy,
                                 @Query(Constants.VOTE_COUNT_PARAM) String voteCount);
}

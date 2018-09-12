package com.omaroid.udacity.movies.utils;

import com.omaroid.udacity.movies.BuildConfig;

public class Constants {

    final public static String MOVIES_BASE_URL = "https://api.themoviedb.org/3/discover/";
    final public static String API_KEY_PARAM = "api_key";
    final public static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public final static String API_KEY = BuildConfig.API_KEY;
    final public static String LANGUAGE_PARAM = "language";

    final public static String TMDB_RESULTS = "results";
    final public static String SORT_BY = "popularity.desc";
    final public static String POPULARITY_SORT = "popularity.desc";
    final public static String VOTE_AVERAGE_SORT = "vote_average.desc";
    final public static String TMDB_GENRES = "genre_ids";
    final public static String TMDB_POSTER = "poster_path";
    final public static String TMDB_OVERVIEW = "overview";
    final public static String TMDB_RELEASE_DATE = "release_date";
    final public static String TMDB_TITLE = "original_title";
    final public static String TMDB_BACKDROP = "backdrop_path";
    final public static String TMDB_VOTE_AVG = "vote_average";
    final public static String VOTE_COUNT = "250";
    final public static String SORT_PARAM = "sort_by";
    final public static String VOTE_COUNT_PARAM = "vote_count.gte";
    final public static String POSTER_SIZE_XLARGE = "w500";
    final public static String POSTER_SIZE_LARGE = "w342";
    final public static String POSTER_SIZE_SMALL = "w185";
    final public static String LANGUAGE_US = "en-US";

    final public static String MOVIE_OBJECT = "movie-object";
}

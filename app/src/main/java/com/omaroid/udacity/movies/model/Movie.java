package com.omaroid.udacity.movies.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.omaroid.udacity.movies.R;

import static com.omaroid.udacity.movies.utils.Constants.IMAGE_BASE_URL;

public class Movie implements Parcelable {

    private String originalTitle;
    private String userRating;
    private String releaseDate;


    private String overView;
    private String posterPath;

    public Movie(String originalTitle, String userRating, String releaseDate, String overView, String posterPath) {

        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.overView = overView;

    }

    private Movie(Parcel in) {
        originalTitle = in.readString();
        userRating = in.readString();
        releaseDate = in.readString();
        overView = in.readString();
        posterPath = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(originalTitle);
        parcel.writeString(userRating);
        parcel.writeString(releaseDate);
        parcel.writeString(overView);
        parcel.writeString(posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverView() {
        return overView;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String buildPosterPath(Context context) {
        String posterWidth = context.getResources().getString(R.string.poster_size);
        return IMAGE_BASE_URL + posterWidth + getPosterPath();
    }
}

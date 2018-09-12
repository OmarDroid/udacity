package com.omaroid.udacity.movies.network;

import com.omaroid.udacity.movies.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder().baseUrl(Constants.MOVIES_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

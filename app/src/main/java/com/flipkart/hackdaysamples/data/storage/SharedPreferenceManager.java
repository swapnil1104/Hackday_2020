package com.flipkart.hackdaysamples.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.flipkart.hackdaysamples.data.models.MovieDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SharedPreferenceManager {

    private static SharedPreferences instance;

    private static String SHARED_PREF_NAME = "com.flipkart.hackday_sample";

    public static String FAV_MOVIE_PREF = "FAV_MOVIE_PREF";

    private static SharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        }
        return instance;
    }

    public static ArrayList<MovieDetails> getFavMovieList(Context context) {
        String moviePrefJson = getInstance(context).getString(FAV_MOVIE_PREF, "");
        if (moviePrefJson.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(moviePrefJson, new TypeToken<ArrayList<MovieDetails>>() {
            }.getType());
        }
    }

    public static void addFavouriteMovie(Context context, MovieDetails movieDetails) {
        ArrayList<MovieDetails> movieDetailsArrayList = getFavMovieList(context);
        if (!movieDetailsArrayList.contains(movieDetails)) {
            movieDetailsArrayList.add(movieDetails);
        }

        saveFavouriteMoviesToPref(context, movieDetailsArrayList);
    }

    public static void saveFavouriteMoviesToPref(Context context, ArrayList<MovieDetails> movieDetails) {
        getInstance(context).edit().putString(FAV_MOVIE_PREF, new Gson().toJson(movieDetails)).apply();
    }

}

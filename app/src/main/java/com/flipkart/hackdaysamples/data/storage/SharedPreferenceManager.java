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

    /**
     * Get instance of the SharedPreference object for the aforementioned pref name
     *
     * @param context
     * @return
     */
    private static SharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        }
        return instance;
    }

    /**
     * This method will return an ArrayList of MovieDetails object,
     * The json string stored in memory is retrived, and is parsed to an ArrayList with the help of GSON library.
     *
     * @param context
     * @return
     */
    public static ArrayList<MovieDetails> getFavMovieList(Context context) {
        String moviePrefJson = getInstance(context).getString(FAV_MOVIE_PREF, "");
        if (moviePrefJson.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(moviePrefJson, new TypeToken<ArrayList<MovieDetails>>() {
            }.getType());
        }
    }

    /**
     * This method will save a new item in the favourite shared preference,
     * It will fetch the json string from memory parse it to ArrayList and append the movieDetail object
     *
     * @param context
     * @param movieDetails
     */
    public static void addFavouriteMovie(Context context, MovieDetails movieDetails) {
        ArrayList<MovieDetails> movieDetailsArrayList = getFavMovieList(context);
        if (!movieDetailsArrayList.contains(movieDetails)) {
            movieDetailsArrayList.add(0, movieDetails);
        }

        saveFavouriteMoviesToPref(context, movieDetailsArrayList);
    }

    /**
     * This method will save the ArrayList of movie details back into the memory
     * Gson library is used to get the JSON string of the Java object, which is saved into the memory.
     * @param context
     * @param movieDetails
     */
    public static void saveFavouriteMoviesToPref(Context context, ArrayList<MovieDetails> movieDetails) {
        getInstance(context).edit().putString(FAV_MOVIE_PREF, new Gson().toJson(movieDetails)).apply();
    }

    g
    public static void deleteMovieFromFavourite(Context context, int position, MovieDetails movieDetails) {
        ArrayList<MovieDetails> movieDetailsArrayList = getFavMovieList(context);
        if (movieDetailsArrayList.size() > position) {
            movieDetailsArrayList.remove(position);
        }

        saveFavouriteMoviesToPref(context, movieDetailsArrayList);
    }
}

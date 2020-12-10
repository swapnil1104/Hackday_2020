package com.flipkart.hackdaysamples.data;

import com.flipkart.hackdaysamples.data.models.MovieDetails;
import com.flipkart.hackdaysamples.data.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * The api to consume is created using interface and various annotations,
 * these annotations on the interface methods and its parameters indicate how a request will be handled.
 *
 * Detailed documentation: https://square.github.io/retrofit/#api-declaration
 */
public interface ApiService {

    @GET("?apikey=ab186342")
    Call<SearchResponse> getSearchResults(@Query("s") String query);


    @GET("?apikey=ab186342")
    Call<MovieDetails> getMovieDetails(@Query("i") String imdbId);
}

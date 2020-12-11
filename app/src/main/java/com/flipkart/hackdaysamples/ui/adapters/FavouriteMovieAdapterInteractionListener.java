package com.flipkart.hackdaysamples.ui.adapters;

import com.flipkart.hackdaysamples.data.models.MovieDetails;

public interface  FavouriteMovieAdapterInteractionListener {
    void deleteMovie(int position, MovieDetails movieDetails);
}

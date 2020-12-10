package com.flipkart.hackdaysamples.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.flipkart.hackdaysamples.R;
import com.flipkart.hackdaysamples.data.models.MovieDetails;
import com.flipkart.hackdaysamples.data.storage.SharedPreferenceManager;
import com.flipkart.hackdaysamples.ui.adapters.FavouriteMovieRecyclerAdapter;

import java.util.ArrayList;

public class FavouriteMoviesActivity extends AppCompatActivity {


    private TextView textFavouriteCount;
    private RecyclerView recyclerFavourite;
    private FavouriteMovieRecyclerAdapter adapter = new FavouriteMovieRecyclerAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);

        initializeViews();
        loadData();
    }

    private void initializeViews() {
        textFavouriteCount = findViewById(R.id.text_favourite_count);
        recyclerFavourite = findViewById(R.id.recycler_favourite);
    }

    private void loadData() {
        ArrayList<MovieDetails> favouriteMovies = SharedPreferenceManager.getFavMovieList(this);

        textFavouriteCount.setText("Favourite Movie Count: " + favouriteMovies.size());

        recyclerFavourite.setAdapter(adapter);
        recyclerFavourite.setLayoutManager(new LinearLayoutManager(this));
        adapter.updateFavouriteMovies(favouriteMovies);
    }
}
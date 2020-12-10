package com.flipkart.hackdaysamples.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flipkart.hackdaysamples.R;
import com.flipkart.hackdaysamples.data.ApiClient;
import com.flipkart.hackdaysamples.data.ApiService;
import com.flipkart.hackdaysamples.data.models.MovieDetails;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String IMDB_ID = "IMDB_ID";

    /**
     * Instance of ApiService which will be used to invoke the declared api endpoints.
     */
    private final ApiService apiService = ApiClient.getInstance().create(ApiService.class);

    private ImageView imagePoster;
    private TextView textTitle;
    private TextView textYear;
    private TextView textPlot;
    private TextView textDirector;
    private TextView textLanguages;
    private TextView textImdbRating;
    private TextView textActors;
    private TextView textMovieRating;
    private TextView textWriters;
    private TextView textGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        initializeViews();
        fetchMovieInformation();
    }

    private void fetchMovieInformation() {
        // The information that we added to the Intent in SearchActivity to launch MovieDetailActivity.
        // The intent object can be retrieved using the getIntent() method.
        String imdbId = getIntent().getStringExtra(MovieDetailActivity.IMDB_ID);

        // Trigger the API call
        getMovieDetails(imdbId);
    }

    private void initializeViews() {
        imagePoster = findViewById(R.id.image_poster);
        textTitle = findViewById(R.id.text_movie_title);
        textYear = findViewById(R.id.text_year);
        textPlot = findViewById(R.id.text_plot);
        textDirector = findViewById(R.id.text_director);
        textLanguages = findViewById(R.id.text_languages);
        textImdbRating = findViewById(R.id.text_imdb_rating);
        textActors = findViewById(R.id.text_actors);
        textMovieRating = findViewById(R.id.text_rating);
        textWriters = findViewById(R.id.text_writers);
        textGenre = findViewById(R.id.text_genre);
    }

    private void getMovieDetails(String imdbId) {
        new Thread(() -> {
            Call<MovieDetails> call = apiService.getMovieDetails(imdbId);
            call.enqueue(new Callback<MovieDetails>() {
                @Override
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                    // Java models can directly be retrieved from response by invoking 'body()'
                    MovieDetails movieDetails = response.body();

                    // What is a Toast?
                    // Refer detailed doc: https://developer.android.com/guide/topics/ui/notifiers/toasts
                    if (movieDetails != null && movieDetails.errorMsg != null && movieDetails.response.equalsIgnoreCase("true")) {
                        Toast.makeText(getApplicationContext(), new Gson().toJson(movieDetails.errorMsg), Toast.LENGTH_LONG).show();
                    } else {
                        if (movieDetails != null) {
                            updateUi(movieDetails);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieDetails> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }


    private void updateUi(MovieDetails movieDetails) {
        // Load poster image
        Glide.with(this).load(movieDetails.poster).into(imagePoster);

        // Setting text value to the TextView references with information from MovieDetails object.
        textTitle.setText(movieDetails.title);
        textYear.setText(movieDetails.year);
        textPlot.setText(movieDetails.plot);
        textLanguages.setText(movieDetails.language);
        textDirector.setText(movieDetails.director);
        textImdbRating.setText(movieDetails.imdbRating);
        textActors.setText(movieDetails.actors);
        textGenre.setText(movieDetails.genre);
        textMovieRating.setText(movieDetails.rated);
        textWriters.setText(movieDetails.writer);
    }

}
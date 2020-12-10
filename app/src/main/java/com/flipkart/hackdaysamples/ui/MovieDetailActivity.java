package com.flipkart.hackdaysamples.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.flipkart.hackdaysamples.R;
import com.flipkart.hackdaysamples.data.ApiClient;
import com.flipkart.hackdaysamples.data.ApiService;
import com.flipkart.hackdaysamples.data.models.MovieDetails;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {


    /**
     * Instance of ApiService which will be used to invoke the declared api endpoints.
     */
    private final ApiService apiService = ApiClient.getInstance().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

    }

}
package com.flipkart.hackdaysamples.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.flipkart.hackdaysamples.R;
import com.flipkart.hackdaysamples.data.ApiClient;
import com.flipkart.hackdaysamples.data.ApiService;
import com.flipkart.hackdaysamples.data.models.SearchResponse;
import com.flipkart.hackdaysamples.ui.adapters.MovieAdapterInteractionListener;
import com.flipkart.hackdaysamples.ui.adapters.MovieSearchRecyclerAdapter;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    /**
     * editMovieQuery will store the view reference of EditText that was inflated in setContentView() method
     */
    private EditText editMovieQuery;

    /**
     * recyclerMovieSearch will store the view reference of RecyclerView inflated in activity_search.xml
     */
    private RecyclerView recyclerMovieSearch;


    private MovieSearchRecyclerAdapter recyclerAdapter = new MovieSearchRecyclerAdapter(this);

    private MovieAdapterInteractionListener interactionListener = new MovieAdapterInteractionListener() {
        @Override
        public void onTitleClicked(String imdbId) {
            // To launch any activity, a new object of Intent class is created and a destination activity class is specified.
            // Detailed documentation at : https://developer.android.com/training/basics/firstapp/starting-activity
            Intent movieDetailActivityIntent = new Intent(SearchActivity.this, MovieDetailActivity.class);

            // To send data between activities, we utilise Bundle classes
            // Detailed documentation at : https://developer.android.com/guide/components/activities/parcelables-and-bundles#sdba
            movieDetailActivityIntent.putExtra(MovieDetailActivity.IMDB_ID, imdbId);

            // launching the activity
            startActivity(movieDetailActivityIntent);
        }
    };

    /**
     * Instance of ApiService which will be used to invoke the declared api endpoints.
     */
    private final ApiService apiService = ApiClient.getInstance().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeViews();
    }

    private void initializeViews() {
        // Creating a view reference to items placed in the XML file, we use findViewById()
        // Please refer to https://developer.android.com/reference/android/view/View#findViewById(int)
        editMovieQuery = findViewById(R.id.edit_movie_query);

        // setting the interaction listener interface
        recyclerAdapter.setInteractionListener(interactionListener);

        recyclerMovieSearch = findViewById(R.id.recycler_movie_search);
        recyclerMovieSearch.setAdapter(recyclerAdapter);

        // What is a layout manager?
        // https://developer.android.com/guide/topics/ui/layout/recyclerview#plan-your-layout
        recyclerMovieSearch.setLayoutManager(new LinearLayoutManager(this));


        // EditText uses <b>TextWatcher</b> interface to watch change made over EditText.
        // For doing this, EditText calls the addTextChangedListener() method.
        editMovieQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateMovieList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no-op
            }
        });
    }

    /*
     * Networking call on Android platform can not be triggered on the main thread,
     * There are many ways to perform the network call;
     * - AsyncTasks https://developer.android.com/reference/android/os/AsyncTask
     * - Runnable https://developer.android.com/reference/java/lang/Runnable
     * - Threads https://developer.android.com/reference/java/lang/Thread
     * - Coroutines https://kotlinlang.org/docs/reference/coroutines-overview.html (Kotlin Only)
     *
     * We will use a Thread invoke 'apiService.getSearchResults()' and show the api response in Toast Ui.
     */
    private void updateMovieList(String queryString) {
        new Thread(() -> {
            Call<SearchResponse> call = apiService.getSearchResults(queryString);
            call.enqueue(new Callback<SearchResponse>() {
                @Override
                public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {

                    // Java models can directly be retrieved from response by invoking 'body()'
                    SearchResponse searchResponse = response.body();

                    // What is a Toast?
                    // Refer detailed doc: https://developer.android.com/guide/topics/ui/notifiers/toasts
                    if (searchResponse != null && searchResponse.errorMsg != null) {
                        Toast.makeText(getApplicationContext(), new Gson().toJson(searchResponse.errorMsg), Toast.LENGTH_LONG).show();
                    } else {
                        if (searchResponse != null && searchResponse.searches != null && searchResponse.searches.size() > 0) {
                            recyclerAdapter.updateSearchResults(searchResponse.searches);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }


}
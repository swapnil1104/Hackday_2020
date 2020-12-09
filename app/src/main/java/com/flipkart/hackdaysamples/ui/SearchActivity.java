package com.flipkart.hackdaysamples.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.flipkart.hackdaysamples.R;

public class SearchActivity extends AppCompatActivity {

    /**
     * editMovieQuery will store the view reference of EditText that was inflated in setContentView() method
     */
    private EditText editMovieQuery;

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

        // EditText uses <b>TextWatcher</b> interface to watch change made over EditText.
        // For doing this, EditText calls the addTextChangedListener() method.
        editMovieQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO:: Make API call with the new query string
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no-op
            }
        });
    }
}
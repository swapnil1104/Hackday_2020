package com.flipkart.hackdaysamples.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flipkart.hackdaysamples.R;
import com.flipkart.hackdaysamples.data.models.Search;

import java.util.ArrayList;


/**
 * What is a RecyclerView adapter?
 * Please read :: https://developer.android.com/guide/topics/ui/layout/recyclerview#implement-adapter
 */
public class MovieSearchRecyclerAdapter extends RecyclerView.Adapter<MovieSearchRecyclerAdapter.ViewHolder> {

    private ArrayList<Search> searchResults;

    public MovieSearchRecyclerAdapter() {
        this.searchResults = new ArrayList<>();
    }

    public MovieSearchRecyclerAdapter(ArrayList<Search> searchResults) {
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_search, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Todo:: Binding the data received from search result into the layout.
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private TextView textYear;
        private TextView textType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.text_title);
            textYear = itemView.findViewById(R.id.text_year);
            textType = itemView.findViewById(R.id.text_type);
        }
    }
}

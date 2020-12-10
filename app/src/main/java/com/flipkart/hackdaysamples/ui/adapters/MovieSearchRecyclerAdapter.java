package com.flipkart.hackdaysamples.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public void updateSearchResults(ArrayList<Search> searchResults) {
        this.searchResults.clear();
        this.searchResults.addAll(searchResults);

        // What is notify dataset changed?
        // https://developer.android.com/reference/android/widget/BaseAdapter#notifyDataSetChanged()
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_search, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Search result = searchResults.get(holder.getAdapterPosition());

        // Todo:: How do we use Glide to load poster image in ImageView

        holder.textTitle.setText(result.title);
        holder.textYear.setText(result.year);
        if (result.type != null) {
            holder.textType.setVisibility(View.VISIBLE);
            holder.textType.setText(result.type);
            if (result.type.equalsIgnoreCase("movie")) {
                holder.textType.setBackgroundResource(R.drawable.blue_bg);
            } else {
                holder.textType.setBackgroundResource(R.drawable.green_bg);
            }
        } else {
            holder.textType.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePoster;

        private TextView textTitle;
        private TextView textYear;
        private TextView textType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagePoster = itemView.findViewById(R.id.image_poster);

            textTitle = itemView.findViewById(R.id.text_title);
            textYear = itemView.findViewById(R.id.text_year);
            textType = itemView.findViewById(R.id.text_type);
        }
    }
}

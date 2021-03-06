package com.flipkart.hackdaysamples.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flipkart.hackdaysamples.R;
import com.flipkart.hackdaysamples.data.models.Search;

import java.util.ArrayList;


/**
 * What is a RecyclerView adapter?
 * Please read :: https://developer.android.com/guide/topics/ui/layout/recyclerview#implement-adapter
 */
public class MovieSearchRecyclerAdapter extends RecyclerView.Adapter<MovieSearchRecyclerAdapter.ViewHolder> {

    private ArrayList<Search> searchResults;

    private Context context;

    private MovieAdapterInteractionListener interactionListener;

    public MovieSearchRecyclerAdapter(Context context) {
        this.context = context;
        this.searchResults = new ArrayList<>();
    }

    public MovieSearchRecyclerAdapter(Context context, ArrayList<Search> searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    public void clearSearchResults() {
        this.searchResults.clear();
        notifyDataSetChanged();
    }

    public void updateSearchResults(ArrayList<Search> searchResults) {
        this.searchResults.clear();
        this.searchResults.addAll(searchResults);

        // What is notify dataset changed?
        // https://developer.android.com/reference/android/widget/BaseAdapter#notifyDataSetChanged()
        notifyDataSetChanged();
    }

    /**
     * Setter method for the MoveAdapterInteractionListener interface
     * @param interactionListener
     */
    public void setInteractionListener(MovieAdapterInteractionListener interactionListener) {
        this.interactionListener = interactionListener;
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

        Glide.with(context)
                .load(result.posterUrl)
                .into(holder.imagePoster);

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

        // Adding an OnClick event listener to the Button View
        holder.buttonDetails.setOnClickListener(v -> {
            if (interactionListener != null) {
                interactionListener.onTitleClicked(result.imdbId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Button buttonDetails;
        private ImageView imagePoster;

        private TextView textTitle;
        private TextView textYear;
        private TextView textType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            buttonDetails = itemView.findViewById(R.id.button_details);

            imagePoster = itemView.findViewById(R.id.image_poster);

            textTitle = itemView.findViewById(R.id.text_title);
            textYear = itemView.findViewById(R.id.text_year);
            textType = itemView.findViewById(R.id.text_type);
        }
    }
}

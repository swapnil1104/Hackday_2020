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
import com.flipkart.hackdaysamples.data.models.MovieDetails;
import com.flipkart.hackdaysamples.data.models.Search;

import java.util.ArrayList;


/**
 * What is a RecyclerView adapter?
 * Please read :: https://developer.android.com/guide/topics/ui/layout/recyclerview#implement-adapter
 */
public class FavouriteMovieRecyclerAdapter extends RecyclerView.Adapter<FavouriteMovieRecyclerAdapter.ViewHolder> {

    private ArrayList<MovieDetails> favouriteMovies;

    private Context context;

    public FavouriteMovieRecyclerAdapter(Context context) {
        this.context = context;
        this.favouriteMovies = new ArrayList<>();
    }

    public FavouriteMovieRecyclerAdapter(Context context, ArrayList<MovieDetails> searchResults) {
        this.context = context;
        this.favouriteMovies = searchResults;
    }

    public void updateFavouriteMovies(ArrayList<MovieDetails> searchResults) {
        this.favouriteMovies.clear();
        this.favouriteMovies.addAll(searchResults);

        // What is notify dataset changed?
        // https://developer.android.com/reference/android/widget/BaseAdapter#notifyDataSetChanged()
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieDetails result = favouriteMovies.get(holder.getAdapterPosition());

        Glide.with(context)
                .load(result.poster)
                .into(holder.imagePoster);

        holder.textTitle.setText(result.title);
        holder.textYear.setText(result.year);
        holder.textActors.setText(result.actors);
        holder.textGenre.setText(result.genre);
        holder.textDirector.setText(result.director);
        holder.textPlot.setText(result.plot);

    }

    @Override
    public int getItemCount() {
        return favouriteMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePoster;

        private TextView textTitle;
        private TextView textYear;

        private TextView textPlot;
        private TextView textDirector;
        private TextView textActors;
        private TextView textGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imagePoster = itemView.findViewById(R.id.image_poster);

            textTitle = itemView.findViewById(R.id.text_title);
            textYear = itemView.findViewById(R.id.text_year);

            textPlot = itemView.findViewById(R.id.text_plot);
            textDirector = itemView.findViewById(R.id.text_director);
            textGenre = itemView.findViewById(R.id.text_genre);
            textActors = itemView.findViewById(R.id.text_actors);
        }
    }
}

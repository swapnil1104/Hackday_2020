package com.flipkart.hackdaysamples.data.models;

import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Title")
    String title;

    @SerializedName("Year")
    String year;

    @SerializedName("imdbID")
    String imdbId;

    @SerializedName("type")
    String type;

    @SerializedName("Poster")
    String posterUrl;

}

package com.flipkart.hackdaysamples.data.models;

import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("imdbID")
    public String imdbId;

    @SerializedName("type")
    public String type;

    @SerializedName("Poster")
    public String posterUrl;

}

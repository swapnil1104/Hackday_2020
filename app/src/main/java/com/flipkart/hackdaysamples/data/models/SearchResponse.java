package com.flipkart.hackdaysamples.data.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {
    @Nullable
    @SerializedName("Search")
    public ArrayList<Search> searches;

    @SerializedName("Response")
    public boolean response;

    @Nullable
    @SerializedName("Error")
    public String errorMsg;
}

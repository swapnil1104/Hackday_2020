package com.flipkart.hackdaysamples.data.models;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("Source")
    public String source;

    @SerializedName("Value")
    public String value;
}
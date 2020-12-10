package com.flipkart.hackdaysamples.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://www.omdbapi.com";

    private static Retrofit instance = null;

    private ApiClient() {
        // no-op
    }

    /**
     * getInstance() will initialize the intance of retrofit only once using the Singleton design pattern.
     *
     * @return instance of Retrofit
     */
    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (ApiClient.class) {
                instance = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())  // A converter class that uses GSON for parsing JSON to Java.
                        .build();
            }
        }
        return instance;
    }

}

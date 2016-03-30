package com.example.atx.popularmoviesapp.callbacks;

import android.net.Uri;

public class ThemovieDBRequestBuilder implements IRequestBuilder{

    public static final String MODE_POPULAR = "popular";
    public static final String MODE_TOP_RATED = "top_rated";

    private String apiKey;
    private String mode;

    public ThemovieDBRequestBuilder(String apiKey, String mode){
        this.apiKey = apiKey;
        this.mode = mode;
    }

    @Override
    public String getRequestString() {
        Uri.Builder uri = new Uri.Builder();
        uri.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(mode)
                .appendQueryParameter("api_key", apiKey);

        String request = uri.build().toString();
        return request;
    }
}

package com.example.atx.popularmoviesapp;

import android.net.Uri;
import android.util.Log;

import com.example.atx.popularmoviesapp.interfaces.IRequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ThemovieDBRequestHandler implements IRequestHandler {

    public static final String MODE_POPULAR = "popular";
    public static final String MODE_TOP_RATED = "top_rated";

    private String apiKey;
    private String mode;

    public ThemovieDBRequestHandler(String apiKey, String mode){
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

    @Override
    public List<MovieInfo> parseResponse(String response) {
        List<MovieInfo> res = new LinkedList<>();
        try {
            final String RES = "results";
            final String POSTER = "poster_path";
            final String OVERVIEW = "overview";
            final String DATE = "release_date";
            final String TITLE = "title";
            final String VOTE = "vote_average";

            JSONObject moviesJson = new JSONObject(response);

            JSONArray top = moviesJson.getJSONArray(RES);

            for(int i = 0; i < top.length(); i++) {

                JSONObject movie = top.getJSONObject(i);

                MovieInfo info = new MovieInfo();
                info.imageLink = movie.getString(POSTER);
                info.description = movie.getString(OVERVIEW);
                info.title = movie.getString(TITLE);
                info.rating = movie.getString(VOTE);
                info.releaseDdate = movie.getString(DATE);

                Log.d("JSON", info.title +" " + info.rating + " " + info.imageLink);
                res.add(info);
            }


        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return null;
    }
}

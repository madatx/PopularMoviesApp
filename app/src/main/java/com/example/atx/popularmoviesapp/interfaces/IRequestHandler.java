package com.example.atx.popularmoviesapp.interfaces;



import com.example.atx.popularmoviesapp.MovieInfo;

import java.util.List;

public interface IRequestHandler {
    String getRequestString();
    List<MovieInfo> parseResponse(String response);
}

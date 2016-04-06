package com.example.atx.popularmoviesapp.interfaces;

import com.example.atx.popularmoviesapp.MovieInfo;

import java.util.List;

public interface IAsyncCallback {
    void setResult(List<MovieInfo> result);
}

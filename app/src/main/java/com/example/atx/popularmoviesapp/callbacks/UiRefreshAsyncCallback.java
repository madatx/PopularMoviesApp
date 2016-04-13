package com.example.atx.popularmoviesapp.callbacks;

import android.content.Context;
import android.widget.GridView;

import com.example.atx.popularmoviesapp.MovieAdapter;
import com.example.atx.popularmoviesapp.MovieInfo;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;

import java.util.List;

public class UiRefreshAsyncCallback implements IAsyncCallback {
    private MovieAdapter adapter;

    public UiRefreshAsyncCallback(MovieAdapter adapter){
        this.adapter = adapter;
    }
    @Override
    public void setResult(List<MovieInfo> result) {
        adapter.refreshData(result);
    }
}

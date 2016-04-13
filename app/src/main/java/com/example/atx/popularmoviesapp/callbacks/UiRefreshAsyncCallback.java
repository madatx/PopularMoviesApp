package com.example.atx.popularmoviesapp.callbacks;

import android.util.Log;

import com.example.atx.popularmoviesapp.MovieAdapter;
import com.example.atx.popularmoviesapp.MovieInfo;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;

import java.util.List;

public class UiRefreshAsyncCallback implements IAsyncCallback {
    private MovieAdapter adapter;
    private String error = null;

    private static String THIS_FILE = UiRefreshAsyncCallback.class.getName();
    public UiRefreshAsyncCallback(MovieAdapter adapter){
        this.adapter = adapter;
    }
    @Override
    public void setResult(List<MovieInfo> result) {
        adapter.refreshData(result);
    }

    @Override
    public void setError(String error) {
        Log.e(THIS_FILE, "ERROR: " + error);
        this.error = error;
    }

    @Override
    public boolean hasError() {
        return error != null;
    }

    @Override
    public String getError() {
        return error;
    }
}

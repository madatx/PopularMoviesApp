package com.example.atx.popularmoviesapp.callbacks;


import android.content.Context;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.atx.popularmoviesapp.MovieAdapter;
import com.example.atx.popularmoviesapp.MovieInfo;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;

import java.util.List;

public class UiAsyncCallback implements IAsyncCallback {

    private MovieAdapter adapter;
    private GridView view;
    private Context context;
    private int viewSize;
    private String error = null;

    public UiAsyncCallback(Context context, GridView view, int viewSize){
        this.view = view;
        this.context = context;
        this.viewSize = viewSize;
    }

    private static String THIS_FILE = UiAsyncCallback.class.getName();
    @Override
    public void setResult(List<MovieInfo> result) {
        adapter = new MovieAdapter(context, result, viewSize);
        view.setAdapter(adapter);
    }

    @Override
    public void setError(String error) {
        Log.e(THIS_FILE, "ERROR: " + error);
        this.error = error;
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean hasError() {
        return error != null;
    }

    @Override
    public String getError() {
        return error;
    }

    public MovieAdapter getAdapter(){return adapter;}
}

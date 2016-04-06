package com.example.atx.popularmoviesapp.callbacks;


import android.content.Context;
import android.util.Log;
import android.widget.GridView;

import com.example.atx.popularmoviesapp.MovieAdapter;
import com.example.atx.popularmoviesapp.MovieInfo;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;

import java.util.List;

public class UiAsyncCallback implements IAsyncCallback {

    private MovieAdapter adapter;
    private GridView view;
    private Context context;

    public UiAsyncCallback(Context context, GridView view){
        this.view = view;
        this.context = context;
    }

    private static String THIS_FILE = UiAsyncCallback.class.getName();
    @Override
    public void setResult(List<MovieInfo> result) {
        adapter = new MovieAdapter(context, result);
        view.setAdapter(adapter);
        Log.d(THIS_FILE, "Adapter set");
    }
}

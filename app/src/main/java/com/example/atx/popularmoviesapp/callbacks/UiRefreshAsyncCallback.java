package com.example.atx.popularmoviesapp.callbacks;

import android.util.Log;
import android.widget.GridView;

import com.example.atx.popularmoviesapp.MovieAdapter;
import com.example.atx.popularmoviesapp.MovieInfo;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;

import java.util.List;

public class UiRefreshAsyncCallback implements IAsyncCallback {
    private GridView view;
    private String error = null;

    private static String THIS_FILE = UiRefreshAsyncCallback.class.getName();
    public UiRefreshAsyncCallback(GridView movieGridView){
        this.view = movieGridView;
    }
    @Override
    public void setResult(List<MovieInfo> result) {
        if((view != null) && (view.getAdapter() != null)) {
            MovieAdapter adapter = (MovieAdapter) view.getAdapter();
            adapter.refreshData(result);
            adapter.notifyDataSetChanged();
        } else {
            Log.e(THIS_FILE, "ERROR: Adapter is empty");
        }
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

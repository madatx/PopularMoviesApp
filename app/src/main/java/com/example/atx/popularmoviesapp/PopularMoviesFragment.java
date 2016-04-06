package com.example.atx.popularmoviesapp;

import android.graphics.Movie;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.atx.popularmoviesapp.callbacks.UiAsyncCallback;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;
import com.example.atx.popularmoviesapp.interfaces.IRequestHandler;
import com.example.atx.popularmoviesapp.tasks.MovieRequestTask;
import com.example.atx.popularmoviesapp.utils.ApiKeySource;

public class PopularMoviesFragment extends Fragment {

    private static final String THIS_FILE = PopularMoviesFragment.class.getName();
    public PopularMoviesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        String key = ApiKeySource.getApiKey(getActivity());

        IRequestHandler builder = new ThemovieDBRequestHandler(key,
                ThemovieDBRequestHandler.MODE_TOP_RATED);

        final GridView gridView = (GridView) v.findViewById(R.id.movies_grid_view);
        IAsyncCallback callback = new UiAsyncCallback(getActivity(), gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieAdapter adapter = (MovieAdapter) gridView.getAdapter();
                MovieInfo item = (MovieInfo)adapter.getItem(position);
                String str = "ITEM CLICKED: " + item.title + " " + item.releaseDdate;
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });

        new MovieRequestTask(callback, builder).execute();

        return v;
    }
}

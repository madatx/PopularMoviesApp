package com.example.atx.popularmoviesapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.atx.popularmoviesapp.callbacks.UiAsyncCallback;
import com.example.atx.popularmoviesapp.callbacks.UiRefreshAsyncCallback;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;
import com.example.atx.popularmoviesapp.interfaces.IRequestHandler;
import com.example.atx.popularmoviesapp.tasks.MovieRequestTask;
import com.example.atx.popularmoviesapp.utils.ApiKeySource;
import com.example.atx.popularmoviesapp.utils.Utils.*;

public class PopularMoviesFragment extends Fragment {


    private MovieAdapter adapter = null;
    private static final String THIS_FILE = PopularMoviesFragment.class.getName();
    public PopularMoviesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void refresh(){
        IAsyncCallback callback = new UiRefreshAsyncCallback(adapter);
        refreshGrid(callback);
    }

    public void refreshGrid(IAsyncCallback callback){
        String key = ApiKeySource.getApiKey(getActivity());
        IRequestHandler builder = new ThemovieDBRequestHandler(key,
                ThemovieDBRequestHandler.MODE_POPULAR);

        new MovieRequestTask(callback, builder).execute();

    }

    private int getImageWidth(){
        WindowManager w = this.getActivity().getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        return metrics.widthPixels/2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_popular_movies, container, false);

        final GridView gridView = (GridView) v.findViewById(R.id.movies_grid_view);
        IAsyncCallback callback = new UiAsyncCallback(getActivity(), gridView, getImageWidth());

        refreshGrid(callback);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieAdapter adapter = (MovieAdapter) gridView.getAdapter();
                MovieInfo item = (MovieInfo) adapter.getItem(position);
//                String str = "ITEM CLICKED: " + item.title + " " + item.releaseDate;
//                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), DetailedMovieActivity.class);
                intent.putExtra(MovieIntentInfo.TITLE, item.title);
                intent.putExtra(MovieIntentInfo.OVERVIEW, item.description);
                intent.putExtra(MovieIntentInfo.POSTER, item.imageLink);
                intent.putExtra(MovieIntentInfo.DATE, item.releaseDate);
                intent.putExtra(MovieIntentInfo.VOTE, item.rating);
                startActivity(intent);
            }
        });


        adapter = (MovieAdapter)gridView.getAdapter();

        return v;
    }
}

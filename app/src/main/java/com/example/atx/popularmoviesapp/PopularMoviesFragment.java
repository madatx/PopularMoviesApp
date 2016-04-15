package com.example.atx.popularmoviesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


    private GridView gridView;
    //private MovieAdapter adapter = null;
    private static final String THIS_FILE = PopularMoviesFragment.class.getName();
    public PopularMoviesFragment() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refresh();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_popular_movies, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh(){
        IAsyncCallback callback = new UiRefreshAsyncCallback(gridView);
        refreshGrid(callback);
    }

    public void refreshGrid(IAsyncCallback callback){
        String requestMode = getPreference();
        String key = ApiKeySource.getApiKey(getActivity());
        IRequestHandler builder = new ThemovieDBRequestHandler(key,
                requestMode);

        new MovieRequestTask(callback, builder).execute();
    }

    private String getPreference(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String res = prefs.getString(
                getString(R.string.prefs_request_mode_key),
                ThemovieDBRequestHandler.MODE_POPULAR);
        return res;
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

        gridView = (GridView) v.findViewById(R.id.movies_grid_view);
        IAsyncCallback callback = new UiAsyncCallback(getActivity(), gridView, getImageWidth());

        refreshGrid(callback);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieAdapter adapter = (MovieAdapter) gridView.getAdapter();
                MovieInfo item = (MovieInfo) adapter.getItem(position);

                Intent intent = new Intent(getActivity(), DetailedMovieActivity.class);
                intent.putExtra(MovieInfo.class.getCanonicalName(), item);

//                intent.putExtra(MovieIntentInfo.TITLE, item.title);
//                intent.putExtra(MovieIntentInfo.OVERVIEW, item.description);
//                intent.putExtra(MovieIntentInfo.POSTER, item.imageLink);
//                intent.putExtra(MovieIntentInfo.DATE, item.releaseDate);
//                intent.putExtra(MovieIntentInfo.VOTE, item.rating);
                startActivity(intent);
            }
        });

        return v;
    }
}

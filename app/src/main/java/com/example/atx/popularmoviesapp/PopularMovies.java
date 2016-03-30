package com.example.atx.popularmoviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.atx.popularmoviesapp.callbacks.IAsyncCallback;
import com.example.atx.popularmoviesapp.callbacks.IRequestBuilder;
import com.example.atx.popularmoviesapp.callbacks.ThemovieDBRequestBuilder;
import com.example.atx.popularmoviesapp.callbacks.UiAsyncCallback;
import com.example.atx.popularmoviesapp.utils.ApiKeySource;

public class PopularMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String key = ApiKeySource.getApiKey(this);

        IRequestBuilder builder = new ThemovieDBRequestBuilder(key,
                ThemovieDBRequestBuilder.MODE_TOP_RATED);
        IAsyncCallback callback = new UiAsyncCallback();

        new MovieRequestTask(callback, builder).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popular_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

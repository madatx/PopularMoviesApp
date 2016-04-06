package com.example.atx.popularmoviesapp.tasks;

import android.os.AsyncTask;

import com.example.atx.popularmoviesapp.MovieInfo;
import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;
import com.example.atx.popularmoviesapp.interfaces.IRequestHandler;
import com.example.atx.popularmoviesapp.utils.HttpApiConnector;

import java.util.List;


public class MovieRequestTask extends AsyncTask<Void, Void, String> {

    private IRequestHandler requestBuilder;
    private IAsyncCallback callback;

    public MovieRequestTask(IAsyncCallback callback,
                            IRequestHandler builder){
        this.callback = callback;
        this.requestBuilder = builder;
    }

    @Override
    protected String doInBackground(Void... params) {
        return HttpApiConnector.request(requestBuilder);
    }

    @Override
    protected void onPostExecute(String s) {
        List<MovieInfo> result = requestBuilder.parseResponse(s);
        callback.setResult(result);
    }
}

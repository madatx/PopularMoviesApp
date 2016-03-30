package com.example.atx.popularmoviesapp;

import android.os.AsyncTask;

import com.example.atx.popularmoviesapp.callbacks.IAsyncCallback;
import com.example.atx.popularmoviesapp.callbacks.IRequestBuilder;
import com.example.atx.popularmoviesapp.utils.HttpApiConnector;


public class MovieRequestTask extends AsyncTask<Void, Void, String> {

    private IRequestBuilder requestBuilder;
    private IAsyncCallback callback;

    public MovieRequestTask(IAsyncCallback callback,
                            IRequestBuilder builder){
        this.callback = callback;
        this.requestBuilder = builder;
    }

    @Override
    protected String doInBackground(Void... params) {
        return HttpApiConnector.request(requestBuilder);
    }

    @Override
    protected void onPostExecute(String s) {
        callback.setResult(s);
    }
}

package com.example.atx.popularmoviesapp;

import android.os.AsyncTask;

import com.example.atx.popularmoviesapp.callbacks.IAsyncCallback;


public class MovieRequestTask extends AsyncTask<String, Void, String> {

    private IAsyncCallback callback;
    public MovieRequestTask(IAsyncCallback c){
        callback = c;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        callback.setResult(s);
    }
}

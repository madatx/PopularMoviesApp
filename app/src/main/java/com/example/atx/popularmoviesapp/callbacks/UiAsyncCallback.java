package com.example.atx.popularmoviesapp.callbacks;


import android.util.Log;

public class UiAsyncCallback implements IAsyncCallback {

    @Override
    public void setResult(String result) {
        Log.d("RESULT", result);
        // Set UI Here
    }
}

package com.example.atx.popularmoviesapp.callbacks;


import android.util.Log;

import com.example.atx.popularmoviesapp.interfaces.IAsyncCallback;

public class UiAsyncCallback implements IAsyncCallback {

    @Override
    public void setResult(String result) {
        Log.d("RESULT", result);
        // Set UI Here
    }
}

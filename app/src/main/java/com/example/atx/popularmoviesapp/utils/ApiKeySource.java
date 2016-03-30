package com.example.atx.popularmoviesapp.utils;

import android.content.Context;

import com.example.atx.popularmoviesapp.R;

public class ApiKeySource {

    private static String getApiKeyFromFile(Context context){
        try {
            String line = context.getString(R.string.apikey);
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String apiKey = null;

    public static String getApiKey(Context context){
        if (apiKey == null){
            apiKey = getApiKeyFromFile(context);
        }
        return apiKey;
    }
}

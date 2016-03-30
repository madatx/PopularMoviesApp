package com.example.atx.popularmoviesapp.utils;

import android.net.Uri;
import android.util.Log;

import com.example.atx.popularmoviesapp.callbacks.IRequestBuilder;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpApiConnector {

    private static final String THIS_FILE = HttpApiConnector.class.getName();

    private static String getResponse(String request){
        if (request == null) return null;

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try{
            URL url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream iStream = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (iStream == null){
                // I need to get to finally block here
                throw new Exception("GetInputStreamFailed");
            }

            reader = new BufferedReader(new InputStreamReader(iStream));
            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0){
                throw new Exception("GotEmptyBufferError");
            }

            result = buffer.toString();
        } catch (Exception ex){
            Log.e(THIS_FILE, "Http Connection Failed", ex);
        } finally {
            if (connection != null){
                connection.disconnect();
            }
            closeNoException(reader);
        }
        return result;
    }

    private static void closeNoException(Closeable toClose){
        if (toClose == null) return;
        try {
            toClose.close();
        } catch (IOException e) {}
    }

    public static String request(IRequestBuilder builder){
        return getResponse(builder.getRequestString());
    }
}

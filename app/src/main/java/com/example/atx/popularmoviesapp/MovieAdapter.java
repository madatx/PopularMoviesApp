package com.example.atx.popularmoviesapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.atx.popularmoviesapp.utils.HttpApiConnector;

import java.util.List;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private List<MovieInfo> movies;
    private int width;

    public MovieAdapter(Context context, List<MovieInfo> movies, int width){
        this.context = context;
        this.movies = movies;
        this.width = width;
    }

    public void refreshData(List<MovieInfo> movies){
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(width, Math.round(width * (float) 1.5)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        MovieInfo info = movies.get(position);
        HttpApiConnector.setSourceImage(context, info.imageLink, imageView);

        return imageView;
    }
}

package com.example.atx.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atx.popularmoviesapp.utils.HttpApiConnector;
import com.example.atx.popularmoviesapp.utils.Utils.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailedMovieActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();

        String title = intent.getStringExtra(MovieIntentInfo.TITLE);
        String description = intent.getStringExtra(MovieIntentInfo.OVERVIEW);
        String poster = intent.getStringExtra(MovieIntentInfo.POSTER);
        String releaseDate = intent.getStringExtra(MovieIntentInfo.DATE);
        String rating = intent.getStringExtra(MovieIntentInfo.VOTE);

        HttpApiConnector.setSourceImage(this, poster, (ImageView) findViewById(R.id.poster_image));

        TextView titleView = (TextView) findViewById(R.id.movie_title);
        titleView.setText(title);

        TextView dateView = (TextView) findViewById(R.id.year_info);
        dateView.setText(getSubstringYear(releaseDate));

        TextView ratingView = (TextView) findViewById(R.id.rating);
        ratingView.setText(rating + "/10");

        TextView descriptionView = (TextView) findViewById(R.id.description_info);
        descriptionView.setText(description);
    }

    public static String getYear(String dateStr){
        if (dateStr != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                Date date = dateFormat.parse(dateStr);
                return "" + (date.getYear() + 1900);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateStr;
    }

    public static String getSubstringYear(String dateStr){
        if ((dateStr != null) && (dateStr.length() > 3)){
            return dateStr.substring(0, 4);
        }
        return dateStr;
    }

}

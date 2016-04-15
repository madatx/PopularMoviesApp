package com.example.atx.popularmoviesapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atx.popularmoviesapp.utils.HttpApiConnector;
import com.example.atx.popularmoviesapp.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailedMovieActivityFragment extends Fragment {

    public DetailedMovieActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detailed_movie, container, false);

        Intent intent = getActivity().getIntent();

        MovieInfo info = intent.getParcelableExtra(MovieInfo.class.getCanonicalName());

        HttpApiConnector.setSourceImage(getActivity(), info.imageLink, (ImageView) v.findViewById(R.id.poster_image));

        TextView titleView = (TextView) v.findViewById(R.id.movie_title);
        titleView.setText(info.title);

        TextView dateView = (TextView) v.findViewById(R.id.year_info);
        dateView.setText(getSubstringYear(info.releaseDate));

        TextView ratingView = (TextView) v.findViewById(R.id.rating);
        ratingView.setText(info.rating + "/10");

        TextView descriptionView = (TextView) v.findViewById(R.id.description_info);
        descriptionView.setText(info.description);

        return v;
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

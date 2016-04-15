package com.example.atx.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieInfo implements Parcelable{
    public String title;
    public String description;
    public String imageLink;

    public String rating;
    public String releaseDate;

    public MovieInfo(){}

    protected MovieInfo(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageLink = in.readString();
        rating = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageLink);
        dest.writeString(rating);
        dest.writeString(releaseDate);
    }
}

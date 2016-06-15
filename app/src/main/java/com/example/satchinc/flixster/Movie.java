package com.example.satchinc.flixster;

import java.util.ArrayList;

/**
 * Created by satchinc on 6/15/16.
 */
public class Movie {
    public String title;
    public String posterUrl;
    public int rating;

    public Movie(String posterUrl, String title, int rating) {
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.title = title;
    }

    public static ArrayList<Movie> getFakeMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        for(int x = 0; x<2;x++){
        movies.add(new Movie("The Social Network", "",75));
        movies.add(new Movie("The Internship", "", 50));
        movies.add(new Movie("The Lion King", "", 100));
        }
        return movies;

    }
    @Override
    public String toString(){
        return title + " - " + rating;
    }

}

package com.example.satchinc.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by satchinc on 6/15/16.
 */
public class Movie  {
    public String posterUrl;
    public double rating;
    public String title;
    public String overview;
    public String backdropUrl;
    public String getBackdropUrl() {
        return backdropUrl;
    }
    public String getOverview() {
        return overview;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public String getTitle() {
        return title;
    }
    public double getRating() {
        return rating;
    }

    public Movie(JSONObject JObj) throws JSONException {
        this.posterUrl = "https://image.tmdb.org/t/p/w342" + JObj.getString("poster_path");
        this.rating =  (JObj.getDouble("vote_average"))/2;//JObj.getInt("vote_average");
        this.title = JObj.getString("original_title");
        this.overview = JObj.getString("overview");
        this.backdropUrl = "https://image.tmdb.org/t/p/w342" + JObj.getString("backdrop_path");
    }
    public static ArrayList<Movie> getMoviesFromJSONArray(JSONArray JArray){
            ArrayList<Movie> movies = new ArrayList<>();
        for(int x=0; x<JArray.length();x++){
            try {
                movies.add(new Movie(JArray.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }
    @Override
    public String toString(){
        return title + " - " + rating;
    }

}

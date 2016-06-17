package com.example.satchinc.flixster;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.satchinc.flixster.Movie.getMoviesFromJSONArray;

/**
 * Created by satchinc on 6/16/16.
 */
public class MoviesClient {

    MoviesAdapter adapter;
    SwipeRefreshLayout swipeContainer;

    public MoviesClient(MoviesAdapter adapter, SwipeRefreshLayout swipeContainer) {
        this.adapter = adapter;
        this.swipeContainer = swipeContainer;
    }

    public void refreshApp(){
        String movieUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> moviesList = new ArrayList<Movie>();
        client.get(movieUrl, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieResults = null;
                try {
                    movieResults = response.getJSONArray("results");
                    ArrayList<Movie> fromArray = new ArrayList<Movie>();
                    moviesList.addAll(getMoviesFromJSONArray(movieResults));
                    Log.d("debug",moviesList.toString());
                    adapter.addAll(moviesList);
                    adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            }
        });
    }
}

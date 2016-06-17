package com.example.satchinc.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {
    ArrayList<Movie> moviesList=  new ArrayList<>();
    SwipeRefreshLayout swipeContainer;
    MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        adapter = new MoviesAdapter(this, moviesList);
        //4. Associate the adapter with ListView
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        lvMovies.setAdapter(adapter);
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = moviesList.get(position);
                Intent i = new Intent(MoviesActivity.this, DetailActivity.class);
                i.putExtra("title", movie.getTitle());
                i.putExtra("overview", movie.getOverview());
                i.putExtra("imageurl", movie.getBackdropUrl());
                i.putExtra("rating", (int) movie.getRating());
                startActivity(i);
            }
        });
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        MoviesClient movieClient = new MoviesClient(adapter, swipeContainer);
        movieClient.refreshApp();
        //ArrayList<Movie> movies = Movie.getFakeMovies();
        //2. Get the listview that we want to populate
        //3. Create and ArrayAdapter
        // ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        //fetchMovies GetMovies = new fetchMovies();
       // ArrayList<Movie> tempDataCheck = new ArrayList<>();
        //tempDataCheck = GetMovies.fetchMoviesList();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {//start of refresh

                    adapter.clear();
                    MoviesClient movieClient = new MoviesClient(adapter, swipeContainer);
                    movieClient.refreshApp();
                    //4. Associate the adapter with ListView
                                //adapter = new MoviesAdapter(, refreshApp());//How do I update adapter to show stuff
                }//end of refresh
            });
        // Configure the refreshing colors

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }






}

//Gotta actually do onItem Click



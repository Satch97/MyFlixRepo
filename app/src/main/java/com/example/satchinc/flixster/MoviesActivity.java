package com.example.satchinc.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity {
    ArrayList<Movie> moviesList=  new ArrayList<>();
    SwipeRefreshLayout swipeContainer;
    MoviesAdapter adapter;
    @BindView(R.id.lvMovies) ListView lvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        adapter = new MoviesAdapter(this, moviesList);
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
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    MoviesClient movieClient = new MoviesClient(adapter, swipeContainer);
                    movieClient.refreshApp();
                }//end of refresh
            });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }






}

//Gotta actually do onItem Click



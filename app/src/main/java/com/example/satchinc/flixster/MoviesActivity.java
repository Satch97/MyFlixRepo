package com.example.satchinc.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);


        //1. Get the actual movies
        ArrayList<Movie> movies = Movie.getFakeMovies();
        //2. Get the listview that we want to populate
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        //3. Create and ArrayAdapter
        ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        //4. Associate the adapter with ListView
        lvMovies.setAdapter(adapter);
    }



}


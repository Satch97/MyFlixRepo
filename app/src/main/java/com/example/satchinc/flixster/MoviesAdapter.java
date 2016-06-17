package com.example.satchinc.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by satchinc on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie>{

        public MoviesAdapter(Context context, ArrayList<Movie> movies){
        super(context,R.layout.item_movie,movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview =(TextView) convertView.findViewById(R.id.tvOverview);

        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        Log.d("MoviesAdapter", "Position: " + position);

        //ivPoster.set
        String imageUri = null;
        //int placeholder = R.drawable.user_placeholder_land;

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageUri = movie.getPosterUrl();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            imageUri = movie.backdropUrl;
        }


        Picasso.with(getContext()).load(imageUri).transform(new RoundedCornersTransformation(10, 10)).
                fit().placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder).into(ivPoster);


        // Return the completed view to render on screen
        return convertView;
    }




}

/* 1)
        */

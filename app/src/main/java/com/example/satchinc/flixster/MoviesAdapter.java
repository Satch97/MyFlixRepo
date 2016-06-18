package com.example.satchinc.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by satchinc on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie>{
        public MoviesAdapter(Context context, ArrayList<Movie> movies){
        super(context,R.layout.item_movie,movies);
    }
        public static class ViewHolder {
        @BindView(R.id.tvTitle)TextView tvTitle;
        @BindView(R.id.tvOverview)TextView tvOverview;
        @BindView(R.id.ivPoster) ImageView ivPoster;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        ViewHolder viewholder;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        }
        else{
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.tvTitle.setText(movie.getTitle());
        viewholder.tvOverview.setText(movie.getOverview());
        convertView.setTag(viewholder);
        String imageUri = null;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageUri = movie.getPosterUrl();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            imageUri = movie.backdropUrl;
        }
        Picasso.with(getContext()).load(imageUri).transform(new RoundedCornersTransformation(10, 10)).
                fit().placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder).into(viewholder.ivPoster);
        // Return the completed view to render on screen
        return convertView;
    }
}



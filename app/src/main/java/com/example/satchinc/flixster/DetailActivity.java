package com.example.satchinc.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("title");
        //getActionBar().setTitle("test");
        String overview = getIntent().getStringExtra("overview");
        String posterUrl= getIntent().getStringExtra("imageurl");
        int rating = getIntent().getIntExtra("rating", 0);
        TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        ImageView ivPoster = (ImageView) findViewById((R.id.ivPoster));
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle) ;

        tvTitle.setText(title);
        mRatingBar.setRating(rating) ;
        tvOverview.setText(overview);

        tvOverview.setMovementMethod(new ScrollingMovementMethod());
        Picasso.with(this).load(posterUrl).transform(new RoundedCornersTransformation(10, 10)).
                fit().placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder).into(ivPoster);

    }
}

package com.example.satchinc.flixster;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.ivPoster) ImageView ivPoster;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        String title = getIntent().getStringExtra("title");
        //getActionBar().setTitle("test");
        String overview = getIntent().getStringExtra("overview");
        String posterUrl= getIntent().getStringExtra("imageurl");
        int rating = getIntent().getIntExtra("rating", 0);

        tvTitle.setText(title);
        ratingBar.setRating(rating) ;
        tvOverview.setText(overview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvOverview.setMovementMethod(new ScrollingMovementMethod());
        Picasso.with(this).load(posterUrl).transform(new RoundedCornersTransformation(10, 10)).
                fit().placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder).into(ivPoster);

    }
}

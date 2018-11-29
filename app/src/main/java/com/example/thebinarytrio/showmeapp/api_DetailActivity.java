package com.example.thebinarytrio.showmeapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class api_DetailActivity extends AppCompatActivity
{
    TextView nameOfMovie, description, userRating, releaseDate;
    ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_movie_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageView = findViewById(R.id.thumbnail_image_header);
        nameOfMovie = findViewById(R.id.title);
        description = findViewById(R.id.description);
        userRating = findViewById(R.id.userrating);
        releaseDate = findViewById(R.id.releasedate);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("original_title"))
        {

            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String movieDescription = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            // glide movie poster or loading placeholder if none is available
            Glide.with(this).load(thumbnail).placeholder(R.drawable.loading).into(imageView);

            nameOfMovie.setText(movieName);
            description.setText(movieDescription);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);

        }
        else
            Toast.makeText(this, "No API data!", Toast.LENGTH_SHORT).show();
    }


    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int veritcalOffset)
            {
                if (scrollRange == -1)
                    scrollRange = appBarLayout.getTotalScrollRange();

                if ((scrollRange + veritcalOffset) == 0)
                {
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isShow = true;
                }
                else if(isShow)
                {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }

        });
    }

}

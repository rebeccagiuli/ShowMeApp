package com.example.thebinarytrio.showmeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView card_genre, card_actor, card_director;
    Button button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);

        // defining cards
        card_genre = findViewById(R.id.cardview_genre);
        card_actor = findViewById(R.id.cardview_actor);
        card_director = findViewById(R.id.cardview_director);

        // add click listener for each card
        card_genre.setOnClickListener(this);
        card_actor.setOnClickListener(this);
        card_director.setOnClickListener(this);
    }


    // switch activity based on card clicked
    @Override
    public void onClick(View v) {
        Intent intent;

        switch(v.getId()) {
            case R.id.cardview_genre:
                intent = new Intent(this, api_MainActivity.class);
                startActivity(intent);
                break;

            case R.id.cardview_actor:
                intent = new Intent(this, ActorListActivity.class);
                startActivity(intent);
                break;

            case R.id.cardview_director:
                intent = new Intent(this, DirectorListActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }


    }
}


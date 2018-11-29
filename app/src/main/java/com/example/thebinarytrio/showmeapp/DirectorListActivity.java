package com.example.thebinarytrio.showmeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DirectorListActivity extends AppCompatActivity implements View.OnClickListener{

    Button button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_director_list);

        // defining buttons
        button_home = findViewById(R.id.button_home);

        // add on click listener for each button
        button_home.setOnClickListener(this);
    }

    // switch activity based on card clicked
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.button_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
